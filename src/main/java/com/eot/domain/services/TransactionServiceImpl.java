package com.eot.domain.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.EOTConstant;
import com.eot.core.LoginTypes;
import com.eot.core.TransactionIdGenerator;
import com.eot.domain.dao.AgentDao;
import com.eot.domain.dao.CommissionAmountDao;
import com.eot.domain.dao.CustomerDao;
import com.eot.domain.dao.DistributerDao;
import com.eot.domain.dao.EntitiDao;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.RetailerDao;
import com.eot.domain.dao.SuperAdminDao;
import com.eot.domain.dao.TransactionDao;
import com.eot.domain.dao.WholesellerDao;
import com.eot.domain.model.Agent;
import com.eot.domain.model.Commission;
import com.eot.domain.model.CommissionAmount;
import com.eot.domain.model.Customer;
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.Login;
import com.eot.domain.model.Retailer;
import com.eot.domain.model.ServiceCharge;
import com.eot.domain.model.ServiceChargeSplit;
import com.eot.domain.model.SuperAdmin;
import com.eot.domain.model.Transaction;
import com.eot.domain.model.Wholeseller;
import com.eot.util.EotException;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionDao;

	@Autowired
	TransactionIdGenerator transactionIdGenerator;

	@Autowired
	AgentDao agentDao;

	@Autowired
	LoginDao loginDao;

	@Autowired
	EntitiDao mgurushDao;

	@Autowired
	DistributerDao distributerDao;

	@Autowired
	RetailerDao retailerDao;

	@Autowired
	WholesellerDao wholesellerDao;

	@Autowired
	CommissionAmountDao commissionAmountDao;

	@Autowired
	SuperAdminDao adminDao;

	@Autowired
	CustomerDao customerDao;

	@SuppressWarnings("unused")
	@Override
	public void deposite(String agentId, Transaction transaction) throws EotException {

		Agent agent = agentDao.findAgentByUserId(agentId);
		Customer customer = customerDao.findCustomerByAccountNo(transaction.getAccountNumber());

		if (agent != null) {
			if (agent.getUserType() == LoginTypes.AGENT.getValue()
					|| agent.getUserType() == LoginTypes.AGENTSOLEMERCHANT.getValue()) {

				if (customer != null) {

					Login login = loginDao.findLoginByUserId(agent.getCreatedBy());

					SuperAdmin superAdmin = adminDao.getAdmin();

					CommissionAmount commissionAmount = new CommissionAmount();

					Double totalCommission = transaction.getTransactionAmount() * (1.0 / 10.0);
					Double customerAmount = transaction.getTransactionAmount() - totalCommission;
					Double serviceCharge = null;

					if (login.getUserType() == LoginTypes.MGURUSH.getValue()) {

						Entiti entiti = mgurushDao.findEntitiByUserId(login.getUserId());

						for (ServiceChargeSplit serviceChargeSplit : entiti.getServiceChargeSplit()) {
							if (transaction.getTransactionType().equals(serviceChargeSplit.getTransactionType())) {
								serviceCharge = customerAmount * (1 / serviceChargeSplit.getServiceChargePercentage());
								customerAmount = customerAmount - serviceCharge;

								ServiceCharge serviceChargeDetails = new ServiceCharge();
								serviceChargeDetails.setEntityId(entiti.getUserId());
								serviceChargeDetails.setTransactionType(serviceChargeSplit.getTransactionType());
								serviceChargeDetails
										.setTransactionTypeName(serviceChargeSplit.getTransactionTypeName());
								serviceChargeDetails.setServiceCharge(serviceCharge);
								superAdmin.getServiceCharge().add(serviceChargeDetails);
								adminDao.saveOrUpadte(superAdmin);
							}

						}

						if (transaction.getTransactionAmount() >= entiti.getTransactionLimit().getTransactionMin()
								&& transaction.getTransactionAmount() <= entiti.getTransactionLimit()
										.getTransactionMax()) {
							Double entityCommission = totalCommission * (1.0 / 10.0);
							totalCommission = totalCommission - entityCommission;
							commissionAmount = addEntitCommission(entityCommission, commissionAmount);
						}

					} else if (login.getUserType() == LoginTypes.DISTRIBUTER.getValue()) {

						Distributer distributer = distributerDao.findDistributerByUserId(login.getUserId());

						Entiti entiti = mgurushDao.findEntitiByUserId(distributer.getEntitiId());

						for (ServiceChargeSplit serviceChargeSplit : entiti.getServiceChargeSplit()) {
							if (transaction.getTransactionType().equals(serviceChargeSplit.getTransactionType())) {
								serviceCharge = customerAmount * (1 / serviceChargeSplit.getServiceChargePercentage());
								ServiceCharge serviceChargeDetails = new ServiceCharge();
								serviceChargeDetails.setEntityId(entiti.getUserId());
								serviceChargeDetails.setTransactionType(serviceChargeSplit.getTransactionType());
								serviceChargeDetails
										.setTransactionTypeName(serviceChargeSplit.getTransactionTypeName());
								serviceChargeDetails.setServiceCharge(serviceCharge);
								superAdmin.getServiceCharge().add(serviceChargeDetails);
								adminDao.saveOrUpadte(superAdmin);
							}
						}
						if (entiti != null) {
							if (transaction.getTransactionAmount() >= entiti.getTransactionLimit().getTransactionMin()
									&& transaction.getTransactionAmount() <= entiti.getTransactionLimit()
											.getTransactionMax()) {
								Double entityCommission = totalCommission * (1.0 / 10.0);
								totalCommission = totalCommission - entityCommission;
								commissionAmount = addEntitCommission(entityCommission, commissionAmount);
							} else {
								throw new EotException(EOTConstant.UNABLE_TO_PROCESS_TRANSACTION);
							}
						} else {
							throw new EotException(EOTConstant.UNABLE_TO_PROCESS);
						}

						if (distributer != null) {
							if (transaction.getTransactionAmount() >= distributer.getTransactionLimit()
									.getTransactionMin()
									&& transaction.getTransactionAmount() <= distributer.getTransactionLimit()
											.getTransactionMax()) {
								for (Commission commission : distributer.getCommissions()) {

									if (transaction.getTransactionAmount() >= commission.getMinAmount()
											&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
										Double distributerCommission = totalCommission
												* (1 / commission.getCommission());
										commissionAmount = addDistributerCommission(distributerCommission,
												commissionAmount);
										totalCommission = totalCommission - distributerCommission;
									}
								}
							}
						}

					} else if (login.getUserType() == LoginTypes.WHOLSELLER.getValue()) {

						Wholeseller wholeseller = wholesellerDao.findWholesellerByUserId(login.getUserId());

						Distributer distributer = distributerDao
								.findDistributerByUserId(wholeseller.getDistributerId());

						Entiti entiti = mgurushDao.findEntitiByUserId(distributer.getEntitiId());

						for (ServiceChargeSplit serviceChargeSplit : entiti.getServiceChargeSplit()) {
							if (transaction.getTransactionType().equals(serviceChargeSplit.getTransactionType())) {
								serviceCharge = customerAmount * (1 / serviceChargeSplit.getServiceChargePercentage());
								customerAmount = customerAmount - serviceCharge;

								ServiceCharge serviceChargeDetails = new ServiceCharge();
								serviceChargeDetails.setEntityId(entiti.getUserId());
								serviceChargeDetails.setTransactionType(serviceChargeSplit.getTransactionType());
								serviceChargeDetails
										.setTransactionTypeName(serviceChargeSplit.getTransactionTypeName());
								serviceChargeDetails.setServiceCharge(serviceCharge);
								superAdmin.getServiceCharge().add(serviceChargeDetails);
								adminDao.saveOrUpadte(superAdmin);
							}
						}

						if (entiti != null) {
							if (transaction.getTransactionAmount() >= entiti.getTransactionLimit().getTransactionMin()
									&& transaction.getTransactionAmount() <= entiti.getTransactionLimit()
											.getTransactionMax()) {
								Double entityCommission = totalCommission * (1.0 / 10.0);
								totalCommission = totalCommission - entityCommission;
								commissionAmount = addEntitCommission(entityCommission, commissionAmount);
							} else {
								throw new EotException(EOTConstant.UNABLE_TO_PROCESS_TRANSACTION);
							}
						} else {
							throw new EotException(EOTConstant.UNABLE_TO_PROCESS);
						}

						if (distributer != null) {
							if (transaction.getTransactionAmount() >= distributer.getTransactionLimit()
									.getTransactionMin()
									&& transaction.getTransactionAmount() <= distributer.getTransactionLimit()
											.getTransactionMax()) {
								for (Commission commission : distributer.getCommissions()) {

									if (transaction.getTransactionAmount() >= commission.getMinAmount()
											&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
										Double distributerCommission = totalCommission
												* (1 / commission.getCommission());
										commissionAmount = addDistributerCommission(distributerCommission,
												commissionAmount);
										totalCommission = totalCommission - distributerCommission;
									}
								}
							}
						}

						if (wholeseller != null) {
							if (transaction.getTransactionAmount() >= wholeseller.getTransactionLimit()
									.getTransactionMin()
									&& transaction.getTransactionAmount() <= wholeseller.getTransactionLimit()
											.getTransactionMax()) {
								for (Commission commission : wholeseller.getCommissions()) {

									if (transaction.getTransactionAmount() >= commission.getMinAmount()
											&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
										Double wholesellerCommission = totalCommission
												* (1.0 / commission.getCommission());
										commissionAmount = addWholesellerCommission(wholesellerCommission,
												commissionAmount);
										totalCommission = totalCommission - wholesellerCommission;
									}
								}
							}
						}

					} else if (login.getUserType() == LoginTypes.RETAILER.getValue()) {

						Retailer retailer = retailerDao.findRetailerByUserId(login.getUserId());

						Wholeseller wholeseller = wholesellerDao.findWholesellerByUserId(retailer.getWholesellerId());

						Distributer distributer = distributerDao
								.findDistributerByUserId(wholeseller.getDistributerId());

						Entiti entiti = mgurushDao.findEntitiByUserId(distributer.getEntitiId());

						for (ServiceChargeSplit serviceChargeSplit : entiti.getServiceChargeSplit()) {
							if (transaction.getTransactionType().equals(serviceChargeSplit.getTransactionType())) {
								serviceCharge = customerAmount * (1 / serviceChargeSplit.getServiceChargePercentage());
								customerAmount = customerAmount - serviceCharge;

								ServiceCharge serviceChargeDetails = new ServiceCharge();
								serviceChargeDetails.setEntityId(entiti.getUserId());
								serviceChargeDetails.setTransactionType(serviceChargeSplit.getTransactionType());
								serviceChargeDetails
										.setTransactionTypeName(serviceChargeSplit.getTransactionTypeName());
								serviceChargeDetails.setServiceCharge(serviceCharge);
								superAdmin.getServiceCharge().add(serviceChargeDetails);
								adminDao.saveOrUpadte(superAdmin);
							}

						}
						if (entiti != null) {
							if (transaction.getTransactionAmount() >= entiti.getTransactionLimit().getTransactionMin()
									&& transaction.getTransactionAmount() <= entiti.getTransactionLimit()
											.getTransactionMax()) {
								Double entityCommission = totalCommission * (1.0 / 10.0);
								totalCommission = totalCommission - entityCommission;
								commissionAmount = addEntitCommission(entityCommission, commissionAmount);
							} else {
								throw new EotException(EOTConstant.UNABLE_TO_PROCESS_TRANSACTION);
							}
						} else {
							throw new EotException(EOTConstant.UNABLE_TO_PROCESS);
						}

						if (distributer != null) {
							if (transaction.getTransactionAmount() >= distributer.getTransactionLimit()
									.getTransactionMin()
									&& transaction.getTransactionAmount() <= distributer.getTransactionLimit()
											.getTransactionMax()) {
								for (Commission commission : distributer.getCommissions()) {

									if (transaction.getTransactionAmount() >= commission.getMinAmount()
											&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
										Double distributerCommission = totalCommission
												* (1 / commission.getCommission());
										commissionAmount = addDistributerCommission(distributerCommission,
												commissionAmount);
										totalCommission = totalCommission - distributerCommission;
									}
								}
							}
						}

						if (wholeseller != null) {
							if (transaction.getTransactionAmount() >= wholeseller.getTransactionLimit()
									.getTransactionMin()
									&& transaction.getTransactionAmount() <= wholeseller.getTransactionLimit()
											.getTransactionMax()) {
								for (Commission commission : wholeseller.getCommissions()) {

									if (transaction.getTransactionAmount() >= commission.getMinAmount()
											&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
										Double wholesellerCommission = totalCommission
												* (1 / commission.getCommission());
										commissionAmount = addWholesellerCommission(wholesellerCommission,
												commissionAmount);
										totalCommission = totalCommission - wholesellerCommission;
									}
								}
							}
						}
						if (retailer != null) {
							if (transaction.getTransactionAmount() >= retailer.getTransactionLimit().getTransactionMin()
									&& transaction.getTransactionAmount() <= retailer.getTransactionLimit()
											.getTransactionMax()) {
								for (Commission commission : retailer.getCommissions()) {

									if (transaction.getTransactionAmount() >= commission.getMinAmount()
											&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
										Double retailerCommission = totalCommission * (1 / commission.getCommission());
										commissionAmount = addRetailerCommission(retailerCommission, commissionAmount);
										totalCommission = totalCommission - retailerCommission;
									}
								}
							}
						}

					}
					commissionAmount.setAgentId(agentId);
					commissionAmount.setAgentCommission(totalCommission);
					commissionAmountDao.addCommissionAmount(commissionAmount);

					customer.setCustomerId(customer.getCustomerId());
					customer.setAccountBalance(customer.getAccountBalance() + customerAmount);

					transaction.setAgentId(agentId);
					transaction.setStatus(EOTConstant.TRANSACTION_STATUS_SUCESS);
					transaction.setTransactionID(transactionIdGenerator.transactionIdGenerator());
					transactionDao.deposite(transaction);
				} else {
					throw new EotException("customer does not exits");
				}

			} else {
				throw new EotException(EOTConstant.UNAUTHORIZED);
			}
		} else {
			throw new EotException(EOTConstant.AGENT_NOT_FOUND);
		}

	}

	public CommissionAmount addEntitCommission(Double commissionDeduction, CommissionAmount commissionAmount) {

		commissionAmount.setEntityCommission(commissionDeduction);
		return commissionAmount;
	}

	public CommissionAmount addDistributerCommission(Double commissionDeduction, CommissionAmount commissionAmount) {
		commissionAmount.setDistributerCommission(commissionDeduction);
		return commissionAmount;

	}

	public CommissionAmount addWholesellerCommission(Double commissionDeduction, CommissionAmount commissionAmount) {
		commissionAmount.setWholesellerCommission(commissionDeduction);
		return commissionAmount;

	}

	public CommissionAmount addRetailerCommission(Double commissionDeduction, CommissionAmount commissionAmount) {
		commissionAmount.setRetailerCommission(commissionDeduction);
		return commissionAmount;

	}

	@Override
	public List<Transaction> miniStatement(String agentId, Transaction transaction) throws EotException {

		Agent agent = agentDao.findAgentByUserId(agentId);
		Customer customer = customerDao.findCustomerByAccountNo(transaction.getAccountNumber());
		if (agent != null) {
			if (customer != null) {
				List<Transaction> list = transactionDao.miniStatements(transaction);

				return list;
			} else {
				throw new EotException(EOTConstant.CUSTOMER_DOESNOT_EXISTS);
			}
		} else {
			throw new EotException(EOTConstant.AGENT_NOT_FOUND);

		}

	}

	@Override
	public Double balanceEnquiry(String accountNumber) throws EotException {
		Customer customer = customerDao.findCustomerByAccountNo(accountNumber);
		if (customer != null) {
			return customer.getAccountBalance();
		} else {
			throw new EotException(EOTConstant.CUSTOMER_DOESNOT_EXISTS);
		}
	}

}
