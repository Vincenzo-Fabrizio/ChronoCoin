package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import service.GoldPriceClient;

@SuppressWarnings({"unusedLambdaParameter", "unused"})
public class MenuGUI extends JFrame {

    private JLabel goldPriceLabel;

    public MenuGUI() {
        setTitle("ɪʟ ᴛᴜᴏ ᴛᴇꜱᴏʀᴏ ɴᴜᴍɪꜱᴍᴀᴛɪᴄᴏ");
        setSize(800, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- HEADER GRAFICO ARTISTICO ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(250, 250, 240));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel title = new JLabel("🅲🅷🆁🅾🅽🅾🅲🅾🅸🅽", SwingConstants.LEFT);
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setForeground(new Color(75, 60, 40));
        headerPanel.add(title, BorderLayout.WEST);

        goldPriceLabel = new JLabel("ᴄᴀʀɪᴄᴀᴍᴇɴᴛᴏ ᴘʀᴇᴢᴢᴏ ᴏʀᴏ...", SwingConstants.RIGHT);
        goldPriceLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        goldPriceLabel.setForeground(new Color(34, 139, 34));
        headerPanel.add(goldPriceLabel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // --- IMMAGINE HEADER DECORATIVA ---
        JLabel imageLabel = new JLabel(new ImageIcon(MenuGUI.class.getClassLoader().getResource("assets/Icon4.png")));
        imageLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(210, 180, 140)));
        add(imageLabel, BorderLayout.CENTER);

        // --- MENU BOTTONI ARTISTICO ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 180, 30, 180));
        buttonPanel.setBackground(new Color(255, 255, 245));

        buttonPanel.add(createRoundedButton("\u2795 𝙰𝙶𝙶𝙸𝚄𝙶𝙸 𝚄𝙽𝙰 𝙽𝚄𝙾𝚅𝙰 𝙼𝙾𝙽𝙴𝚃𝙰", new Color(204, 229, 255)));
        buttonPanel.add(createRoundedButton("\uD83D\uDCDC 𝚅𝙸𝚂𝚄𝙰𝙻𝙸𝚉𝚉𝙰 𝙻𝙰 𝙲𝙾𝙻𝙻𝙴𝚉𝙸𝙾𝙽𝙴", new Color(230, 255, 204)));
        buttonPanel.add(createRoundedButton("\u274C 𝚁𝙸𝙼𝚄𝙾𝚅𝙸 𝚄𝙽𝙰 𝙼𝙾𝙽𝙴𝚃𝙰", new Color(255, 204, 204)));
        buttonPanel.add(createRoundedButton("\uD83D\uDCB0 𝙰𝙶𝙶𝙸𝙾𝚁𝙽𝙰 𝚀𝚄𝙾𝚃𝙰𝚉𝙸𝙾𝙽𝙴 𝙾𝚁𝙾", new Color(255, 239, 179)));
        buttonPanel.add(createRoundedButton("\uD83D\uDD12 𝙲𝙷𝙸𝚄𝙳𝙸 𝙰𝙿𝙿𝙻𝙸𝙲𝙰𝚉𝙸𝙾𝙽𝙴", new Color(240, 240, 240)));

        add(buttonPanel, BorderLayout.SOUTH);

        // --- AZIONI BOTTONI ---
        Component[] buttons = buttonPanel.getComponents();
        ((JButton) buttons[0]).addActionListener(event -> Menu.executeOption(1));
        ((JButton) buttons[1]).addActionListener(event -> Menu.executeOption(2));
        ((JButton) buttons[2]).addActionListener(event -> Menu.executeOption(3));
        ((JButton) buttons[3]).addActionListener(event -> updateGoldPrice());
        ((JButton) buttons[4]).addActionListener(event -> System.exit(0));

        // --- AGGIORNAMENTO AUTOMATICO PREZZO ORO ---
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updateGoldPrice());
            }
        }, 0, 10000);
    }

    private JButton createRoundedButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        button.setFocusPainted(false);
        button.setFont(new Font("Georgia", Font.PLAIN, 16));
        button.setForeground(new Color(60, 60, 60));
        button.setBackground(bgColor);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void updateGoldPrice() {
        try {
            GoldPriceClient goldClient = new GoldPriceClient();
            var gold = goldClient.getGoldPrice("EUR");

            gold.price_gram_22k = gold.price_gram_24k * 0.917;
            String prezzo24 = String.format("%.2f", gold.price_gram_24k);
            String prezzo22 = String.format("%.2f", gold.price_gram_22k);

            String testo = "<html>"
                    + "\uD83D\uDCB0 24K: <b>" + prezzo24 + " €</b><br>"
                    + "\uD83D\uDCB0 22K: <b>" + prezzo22 + " €</b><br>"
                    + "</html>";

            goldPriceLabel.setText(testo);
        } catch (Exception e) {
            goldPriceLabel.setText("❌ ᴇʀʀᴏʀᴇ ᴀɢɢɪᴏʀɴᴀᴍᴇɴᴛᴏ ᴏʀᴏ");
            goldPriceLabel.setForeground(Color.RED);
            e.printStackTrace();
        }
    }

};