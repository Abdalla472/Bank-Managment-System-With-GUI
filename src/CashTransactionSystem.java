import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Before running you need to add a client first at the admin tab, so you can start doing client operations
//Please add the Chinese Rocks Rg (AKA Red Dead Redemption 2 font) for better experience
public class CashTransactionSystem {


    static LinkedList<String> clients = new LinkedList<>();
    static LinkedList<String> transactions = new LinkedList<>();
    static Queue<String> requests = new LinkedList<>();
    static Stack<String> undoStack = new Stack<>();



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> mainTab());
    }

    private static void mainTab() {
        JFrame frame = new JFrame("Cash Transaction Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        ImageIcon image = new ImageIcon("app.png");
        frame.setIconImage(image.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JButton clientButton = new noFocusButton("Client Operations");
        clientButton.addActionListener(e -> clientTab());
        panel.add(clientButton);


        JButton adminButton = new noFocusButton("Admin Operations");
        adminButton.addActionListener(e -> adminTab());
        panel.add(adminButton);
        frame.add(panel);
        frame.setVisible(true);

    }

    private static void clientTab() {
        JFrame clientFrame = new JFrame("Client Operations");
        clientFrame.setSize(400, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(new Color(23, 22, 22));

        ImageIcon image = new ImageIcon("app.png");
        clientFrame.setIconImage(image.getImage());

        JButton depositButton = new noFocusButton("Deposit");
        depositButton.addActionListener(e -> deposit());
        panel.add(depositButton);

        JButton withdrawButton = new noFocusButton("Withdraw");
        withdrawButton.addActionListener(e -> withdraw());
        panel.add(withdrawButton);

        JButton transferButton = new noFocusButton("Transfer Credit");
        transferButton.addActionListener(e -> transferCredit());
        panel.add(transferButton);

        JButton requestButton = new noFocusButton("Request Money");
        requestButton.addActionListener(e -> requestMoney());
        panel.add(requestButton);

        JButton showTransactionsButton = new noFocusButton("Show Transactions");
        showTransactionsButton.addActionListener(e -> showTransactions());
        panel.add(showTransactionsButton);

        JButton showRequestsButton = new noFocusButton("Show Requests");
        showRequestsButton.addActionListener(e -> showRequests());
        panel.add(showRequestsButton);

        JButton undoButton = new noFocusButton("Undo Last Transaction");
        undoButton.addActionListener(e -> undoLastTransaction());
        panel.add(undoButton);

        clientFrame.add(panel);
        clientFrame.setVisible(true);
    }

    private static void adminTab() {
        JFrame adminFrame = new JFrame("Admin Operations");
        adminFrame.setSize(400, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.setBackground(new Color(23, 22, 22));

        ImageIcon image = new ImageIcon("app.png");
        adminFrame.setIconImage(image.getImage());

        JButton addClientButton = new noFocusButton("Add Client");
        addClientButton.addActionListener(e -> addClient());
        panel.add(addClientButton);

        JButton removeClientButton = new noFocusButton("Remove Client");
        removeClientButton.addActionListener(e -> removeClient());
        panel.add(removeClientButton);

        JButton editClientInfoButton = new noFocusButton("Edit Client Info");
        editClientInfoButton.addActionListener(e -> editClientInfo());
        panel.add(editClientInfoButton);

        JButton showAllClientsButton = new noFocusButton("Show All Clients");
        showAllClientsButton.addActionListener(e -> showAllClients());
        panel.add(showAllClientsButton);

        JButton showAllTransactionsButton = new noFocusButton("Show All Transactions");
        showAllTransactionsButton.addActionListener(e -> showAllTransactions());
        panel.add(showAllTransactionsButton);

        adminFrame.add(panel);
        adminFrame.setVisible(true);
    }

    private static void deposit() {
        String client = selectClient();
        String amount = JOptionPane.showInputDialog("Enter amount to deposit:");
        String transaction = client + " deposited $" + amount;
        transactions.addFirst(transaction);
        undoStack.push(transaction);
        JOptionPane.showMessageDialog(null, transaction);
    }

    private static void withdraw() {
        String client = selectClient();
        String amount = JOptionPane.showInputDialog("Enter amount to withdraw:");
        String transaction = client + " withdrew $" + amount;
        transactions.addFirst(transaction);
        undoStack.push(transaction);
        JOptionPane.showMessageDialog(null, transaction);
    }

    private static void transferCredit() {
        String fromClient = selectClient();
        String toClient = JOptionPane.showInputDialog("Enter recipient client name:");
        String amount = JOptionPane.showInputDialog("Enter amount to transfer:");
        String transaction = fromClient + " transferred $" + amount + " to " + toClient;
        transactions.addFirst(transaction);
        undoStack.push(transaction);
        JOptionPane.showMessageDialog(null, transaction);
    }

    private static void requestMoney() {
        String fromClient = selectClient();
        String amount = JOptionPane.showInputDialog("Enter amount to request:");
        String request = fromClient + " requested $" + amount;
        requests.add(request);
        JOptionPane.showMessageDialog(null, request);
    }

    private static void showTransactions() {
        StringBuilder message = new StringBuilder("Transactions:\n");
        for (String transaction : transactions) {
            message.append(transaction).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private static void showRequests() {
        StringBuilder message = new StringBuilder("Requests:\n");
        for (String request : requests) {
            message.append(request).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }


    private static void addClient() {
        String client = JOptionPane.showInputDialog("Enter client name:");
        clients.add(client);
        JOptionPane.showMessageDialog(null, "Client " + client + " added.");
    }

    private static void removeClient() {
        String client = selectClient();
        clients.remove(client);
        JOptionPane.showMessageDialog(null, "Client " + client + " removed.");
    }

    private static void editClientInfo() {
        String client = selectClient();
        String newClientInfo = JOptionPane.showInputDialog("Enter new client name:", client);
        clients.set(clients.indexOf(client), newClientInfo);
        JOptionPane.showMessageDialog(null, "Client info updated to " + newClientInfo);
    }

    private static void showAllClients() {
        StringBuilder message = new StringBuilder("Clients:\n");
        for (String client : clients) {
            message.append(client).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private static void showAllTransactions() {
        StringBuilder message = new StringBuilder("All Transactions:\n");
        for (String transaction : transactions) {
            message.append(transaction).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    private static void undoLastTransaction() {
        if (!undoStack.isEmpty()) {
            String lastTransaction = undoStack.pop();
            transactions.remove(lastTransaction);
            JOptionPane.showMessageDialog(null, "Undone: " + lastTransaction);
        } else {
            JOptionPane.showMessageDialog(null, "No transaction to undo.");
        }
    }

    private static String selectClient() {
        Object[] clientArray = clients.toArray();
        return (String) JOptionPane.showInputDialog(null, "Select Client", "Client",
                JOptionPane.QUESTION_MESSAGE, null, clientArray, clientArray[0]);
    }
}
