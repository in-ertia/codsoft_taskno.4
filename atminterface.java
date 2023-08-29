import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM extends JFrame {
    private BankAccount bankAccount;
    private JTextArea outputTextArea;

    public ATM(BankAccount account) {
        setTitle("task 3 ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bankAccount = account;
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.append("Current balance: $" + bankAccount.getBalance() + "\n");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depositAmountStr = JOptionPane.showInputDialog("Enter deposit amount: $");
                if (depositAmountStr != null) {
                    double depositAmount = Double.parseDouble(depositAmountStr);
                    bankAccount.deposit(depositAmount);
                    outputTextArea.append("Deposit successful.:\n");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String withdrawalAmountStr = JOptionPane.showInputDialog("Enter withdrawal amount: $");
                if (withdrawalAmountStr != null) {
                    double withdrawalAmount = Double.parseDouble(withdrawalAmountStr);
                    if (bankAccount.withdraw(withdrawalAmount)) {
                        outputTextArea.append("Withdrawal successful.\n");
                    } else {
                        outputTextArea.append("Insufficient balance.\n");
                    }
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.append("Thank you for using the ATM!\n");
            }
        });
    }
}

public class atminterface {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BankAccount userAccount = new BankAccount(0.0); 
                ATM atm = new ATM(userAccount);
                atm.setVisible(true);
            }
        });
    }
}


