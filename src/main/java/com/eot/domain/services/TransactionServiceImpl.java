package com.eot.domain.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eot.core.LoginTypes;
import com.eot.core.TransactionIdGenerator;
import com.eot.domain.dao.AgentDao;
import com.eot.domain.dao.CommissionAmountDao;
import com.eot.domain.dao.DistributerDao;
import com.eot.domain.dao.EntitiDao;
import com.eot.domain.dao.LoginDao;
import com.eot.domain.dao.RetailerDao;
import com.eot.domain.dao.TransactionDao;
import com.eot.domain.dao.WholesellerDao;
import com.eot.domain.model.Agent;
import com.eot.domain.model.Commission;
import com.eot.domain.model.CommissionAmount;
import com.eot.domain.model.Distributer;
import com.eot.domain.model.Entiti;
import com.eot.domain.model.Login;
import com.eot.domain.model.Retailer;
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

	@Override
	public void deposite(String agentId, Transaction transaction) throws EotException {

		Agent agent = agentDao.findAgentByUserId(agentId);

		if (agent != null) {
			if (agent.getUserType() == LoginTypes.AGENT.getValue()) {

				Login login = loginDao.findLoginByUserId(agent.getCreatedBy());
				
				CommissionAmount commissionAmount = new CommissionAmount();

				Double totalCommission = transaction.getTransactionAmount() * (1.0 / 10.0);;

				if (login.getUserType() == LoginTypes.MGURUSH.getValue()) {
					
					Entiti entiti = mgurushDao.findMgurushByUserId(login.getUserId());
					
					Double entityCommission = totalCommission * (1.0 / 10.0);
					totalCommission = totalCommission - entityCommission;
					commissionAmount = addEntitCommission(entityCommission, commissionAmount);

				} else if (login.getUserType() == LoginTypes.DISTRIBUTER.getValue()) {

					Distributer distributer = distributerDao.findDistributerByUserId(login.getUserId());
					
					Double entityCommission = totalCommission * (1.0 / 10.0);
					totalCommission = totalCommission - entityCommission;
					commissionAmount = addEntitCommission(entityCommission, commissionAmount);

					for (Commission commission : distributer.getCommissions()) {

						if (transaction.getTransactionAmount() >= commission.getMinAmount()
								&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
							Double distributerCommission = totalCommission
									* (1 / commission.getCommission());
							commissionAmount = addDistributerCommission(distributerCommission, commissionAmount);
							totalCommission = totalCommission - distributerCommission;
						}
					}

				} else if (login.getUserType() == LoginTypes.WHOLSELLER.getValue()) {

					Wholeseller wholeseller = wholesellerDao.findWholesellerByUserId(login.getUserId());

					Distributer distributer = distributerDao.findDistributerByUserId(login.getUserId());
					
					Double entityCommission = totalCommission * (1.0 / 10.0);
					totalCommission = totalCommission - entityCommission;
					commissionAmount = addEntitCommission(entityCommission, commissionAmount);

					for (Commission commission : distributer.getCommissions()) {

						if (transaction.getTransactionAmount() >= commission.getMinAmount()
								&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
							Double distributerCommission =totalCommission
									* (1 / commission.getCommission());
							commissionAmount = addDistributerCommission(distributerCommission, commissionAmount);
							totalCommission = totalCommission - distributerCommission;
						}
					}

					for (Commission commission : wholeseller.getCommissions()) {

						if (transaction.getTransactionAmount() >= commission.getMinAmount()
								&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
							Double wholesellerCommission = totalCommission
									* (1 / commission.getCommission());
							commissionAmount = addWholesellerCommission(wholesellerCommission, commissionAmount);
							totalCommission = totalCommission - wholesellerCommission;
						}
					}

				} else if (login.getUserType() == LoginTypes.RETAILER.getValue()) {

					Retailer retailer = retailerDao.findRetailerByUserId(login.getUserId());

					Wholeseller wholeseller = wholesellerDao.findWholesellerByUserId(retailer.getWholesellerId());

					Distributer distributer = distributerDao.findDistributerByUserId(wholeseller.getDistributerId());
					
					Double entityCommission = totalCommission * (1.0 / 10.0);
					totalCommission = totalCommission - entityCommission;
					commissionAmount = addEntitCommission(entityCommission, commissionAmount);

					for (Commission commission : distributer.getCommissions()) {

						if (transaction.getTransactionAmount() >= commission.getMinAmount()
								&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
							Double distributerCommission = totalCommission
									* (1 / commission.getCommission());
							commissionAmount = addDistributerCommission(distributerCommission, commissionAmount);
							totalCommission = totalCommission - distributerCommission;
						}
					}

					for (Commission commission : wholeseller.getCommissions()) {

						if (transaction.getTransactionAmount() >= commission.getMinAmount()
								&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
							Double wholesellerCommission =totalCommission
									* (1 / commission.getCommission());
							commissionAmount = addWholesellerCommission(wholesellerCommission, commissionAmount);
							totalCommission = totalCommission - wholesellerCommission;
						}
					}
					for (Commission commission : retailer.getCommissions()) {

						if (transaction.getTransactionAmount() >= commission.getMinAmount()
								&& transaction.getTransactionAmount() <= commission.getMaxAmount()) {
							Double retailerCommission = totalCommission
									* (1 / commission.getCommission());
							commissionAmount = addRetailerCommission(retailerCommission, commissionAmount);
							totalCommission = totalCommission - retailerCommission;
						}
					}

				}
				commissionAmount.setAgentId(agentId);
				commissionAmount.setAgentCommission(totalCommission);
				commissionAmountDao.addCommissionAmount(commissionAmount);
				transaction.setStatus(1000);
				transaction.setTransactionDate(new Date());
				transaction.setTransactionID(transactionIdGenerator.transactionIdGenerator());
				transactionDao.deposite(transaction);
			} else {
				throw new EotException("not authorized");
			}
		} else {
			throw new EotException("Agent not found");
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

}
