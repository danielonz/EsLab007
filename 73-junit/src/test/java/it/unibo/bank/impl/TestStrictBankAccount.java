package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {
    private static final int AMOUNT = 100;
    private static final int ACCEPTABLE_MESSAGE_LENGTH = 10;

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private StrictBankAccount strictBankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.strictBankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(mRossi, strictBankAccount.getAccountHolder());
        assertEquals(0.0, strictBankAccount.getBalance());
        assertEquals(0, strictBankAccount.getTransactionsCount());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        strictBankAccount.deposit(1, AMOUNT);
        strictBankAccount.chargeManagementFees(1);
        assertEquals(AMOUNT-strictBankAccount.MANAGEMENT_FEE-
            (strictBankAccount.TRANSACTION_FEE*
            (strictBankAccount.getTransactionsCount()+1)),
            strictBankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        //depositing an amount to be sure that the problem is made by the negative
        //amount of money, not from withdrawing too much :)
        strictBankAccount.deposit(1, AMOUNT);
        try {
            strictBankAccount.withdraw(1,-AMOUNT);
            Assertions.fail("it shouldnt be possible to withdraw a negative amount");
        } catch (final IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        strictBankAccount.deposit(1, AMOUNT*2);
        try {
            strictBankAccount.withdraw(1, AMOUNT);
            Assertions.fail("it shouldnt be possible to withdraw more than the deposited");
        } catch (final IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }
       
    }
}
