package ui;

import model.Coin;
import model.OptionConservation;
import model.NumismaticRarity;
import service.CoinApiClient;
import service.GoldPriceClient;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Menu {

    public static void executeOption(int choice) {
        CoinApiClient client = new CoinApiClient();

        switch (choice) {
            case 1 -> {
                JPanel mainPanel = new JPanel(new GridBagLayout());
                mainPanel.setBackground(new Color(173, 216, 230));
                mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
            
                Dimension labelDim = new Dimension(150, 35);
                Dimension fieldDim = new Dimension(430, 40);
            
                JTextField nameField     = new JTextField();
                JTextField yearField     = new JTextField();
                JTextField materialField = new JTextField();
                JTextField weightField   = new JTextField();
                JTextField diameterField = new JTextField();
                JTextField heightField   = new JTextField();
                JTextField priceField    = new JTextField();
                JComboBox<OptionConservation> obverseBox = new JComboBox<>(OptionConservation.values());
                JComboBox<OptionConservation> reverseBox = new JComboBox<>(OptionConservation.values());
                JComboBox<NumismaticRarity> degreeBox    = new JComboBox<>(NumismaticRarity.values());
            
                for (JTextField tf : new JTextField[]{
                        nameField, yearField, materialField,
                        weightField, diameterField, heightField, priceField
                }) {
                    tf.setPreferredSize(fieldDim);
                    tf.setMaximumSize(fieldDim);
                    tf.setMinimumSize(fieldDim);
                    tf.setHorizontalAlignment(JTextField.LEFT);
                }
            
                JTextArea noteArea = new JTextArea(3, 1);
                noteArea.setLineWrap(true);
                noteArea.setWrapStyleWord(true);
                JScrollPane noteScroll = new JScrollPane(noteArea);
                noteScroll.setPreferredSize(new Dimension(fieldDim.width, fieldDim.height * 3));
                noteScroll.setMaximumSize(noteScroll.getPreferredSize());
            
                JButton obverseFileBtn = new JButton("📷 𝙵𝙾𝚃𝙾 𝙵𝚁𝙾𝙽𝚃𝙴");
                JButton reverseFileBtn = new JButton("📷 𝙵𝙾𝚃𝙾 𝚁𝙴𝚃𝚁𝙾");
                JLabel obversePath = new JLabel(" ");
                JLabel reversePath = new JLabel(" ");
                obverseFileBtn.addActionListener(e -> {
                    JFileChooser chooser = new JFileChooser();
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                        obversePath.setText(chooser.getSelectedFile().getAbsolutePath());
                });
                reverseFileBtn.addActionListener(e -> {
                    JFileChooser chooser = new JFileChooser();
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                        reversePath.setText(chooser.getSelectedFile().getAbsolutePath());
                });
            
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.fill   = GridBagConstraints.NONE;
            
                int row = 0;
            
                JLabel nameLabel = new JLabel("𝙽𝚘𝚖𝚎:");
                nameLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(nameLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(nameField, gbc);
                row++;
            
                JLabel yearLabel = new JLabel("𝙰𝚗𝚗𝚘:");
                yearLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(yearLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(yearField, gbc);
                row++;
            
                JLabel materialLabel = new JLabel("𝙼𝚊𝚝𝚎𝚛𝚒𝚊𝚕𝚎:");
                materialLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(materialLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(materialField, gbc);
                row++;
            
                JLabel weightLabel = new JLabel("𝙿𝚎𝚜𝚘 (𝚐):");
                weightLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(weightLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(weightField, gbc);
                row++;
            
                JLabel diameterLabel = new JLabel("𝙳𝚒𝚊𝚖𝚎𝚝𝚛𝚘 (𝚖𝚖):");
                diameterLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(diameterLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(diameterField, gbc);
                row++;
            
                JLabel heightLabel = new JLabel("𝙰𝚕𝚝𝚎𝚣𝚣𝚊 (𝚖𝚖):");
                heightLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(heightLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(heightField, gbc);
                row++;
            
                // Prezzo
                JLabel priceLabel = new JLabel("𝙿𝚛𝚎𝚣𝚣𝚘 (€):");
                priceLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(priceLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(priceField, gbc);
                row++;
            
                JLabel obvLabel = new JLabel("𝙲𝚘𝚗𝚜𝚎𝚛𝚟𝚊𝚣𝚒𝚘𝚗𝚎 𝚏𝚛𝚘𝚗𝚝𝚎:");
                obvLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(obvLabel, gbc);
                gbc.gridx = 1;
                obverseBox.setPreferredSize(fieldDim);
                mainPanel.add(obverseBox, gbc);
                row++;
            
                JLabel revLabel = new JLabel("𝙲𝚘𝚗𝚜𝚎𝚛𝚟𝚊𝚣𝚒𝚘𝚗𝚎 𝚛𝚎𝚝𝚛𝚘:");
                revLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(revLabel, gbc);
                gbc.gridx = 1;
                reverseBox.setPreferredSize(fieldDim);
                mainPanel.add(reverseBox, gbc);
                row++;
        
                JLabel degLabel = new JLabel("𝙶𝚛𝚊𝚍𝚘 𝚗𝚞𝚖𝚒𝚜𝚖𝚊𝚝𝚒𝚌𝚘:");
                degLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(degLabel, gbc);
                gbc.gridx = 1;
                degreeBox.setPreferredSize(fieldDim);
                mainPanel.add(degreeBox, gbc);
                row++;
            
                JLabel noteLabel = new JLabel("𝙽𝚘𝚝𝚎:");
                noteLabel.setPreferredSize(labelDim);
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(noteLabel, gbc);
                gbc.gridx = 1;
                mainPanel.add(noteScroll, gbc);
                row++;
            
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(obverseFileBtn, gbc);
                gbc.gridx = 1;
                mainPanel.add(obversePath, gbc);
                row++;
        
                gbc.gridx = 0; gbc.gridy = row;
                mainPanel.add(reverseFileBtn, gbc);
                gbc.gridx = 1;
                mainPanel.add(reversePath, gbc);
                row++;
            
                gbc.gridx = 0; gbc.gridy = row;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.insets = new Insets(20, 5, 5, 5);
                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(new Color(173, 216, 230));
                JButton ok     = new JButton("✅ 𝙸𝙽𝚂𝙴𝚁𝙸𝚂𝙲𝙸");
                JButton cancel = new JButton("❌ 𝙰𝙽𝙽𝚄𝙻𝙻𝙰");
                buttonPanel.add(ok);
                buttonPanel.add(cancel);
                mainPanel.add(buttonPanel, gbc);
        
                JDialog dialog = new JDialog((Frame) null, "ɪɴꜱᴇʀɪꜱᴄɪ ᴜɴᴀ ɴᴜᴏᴠᴀ ᴍᴏɴᴇᴛᴀ", true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.getContentPane().add(mainPanel);
                dialog.pack();
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(null);
        
                ok.addActionListener(e -> {
                    try {
                        Coin coin = new Coin();
                        coin.setName(nameField.getText());
                        coin.setYear(Integer.parseInt(yearField.getText()));
                        coin.setMaterial(materialField.getText());
                        coin.setWeight(Double.parseDouble(weightField.getText()));
                        coin.setDiameter(Double.parseDouble(diameterField.getText()));
                        coin.setHeight(Double.parseDouble(heightField.getText()));
                        coin.setPrice(Double.parseDouble(priceField.getText()));
                        coin.setConservationObverse((OptionConservation) obverseBox.getSelectedItem());
                        coin.setConservationReverse((OptionConservation) reverseBox.getSelectedItem());
                        coin.setDegree((NumismaticRarity) degreeBox.getSelectedItem());
                        coin.setNote(noteArea.getText());
                        coin.setPhotoPathObverse(obversePath.getText());
                        coin.setPhotoPathReverse(reversePath.getText());
            
                        Coin inserted = client.createCoin(coin);
                        JOptionPane.showMessageDialog(dialog,
                            "✅ 𝙼𝙾𝙽𝙴𝚃𝙰 𝙸𝙽𝚂𝙴𝚁𝙸𝚃𝙰: " + inserted.getName());
                        dialog.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(dialog,
                            "❌ 𝙴𝚁𝚁𝙾𝚁𝙴: " + ex.getMessage(),
                            "ᴇʀʀᴏʀᴇ", JOptionPane.ERROR_MESSAGE);
                    }
                });
                cancel.addActionListener(e -> dialog.dispose());
            
                dialog.setVisible(true);
            }

            case 2 -> {
                try {
                    List<Coin> coins = client.getAllCoins();
                    List<Coin> validCoins = coins.stream().filter(c -> c != null && c.getID() != null && !c.getID().isBlank()).toList();
            
                    if (validCoins.isEmpty()) {
                        JPanel emptyPanel = new JPanel();
                        emptyPanel.setOpaque(true);
                        emptyPanel.setBackground(new Color(101, 67, 33));
                        emptyPanel.setPreferredSize(new Dimension(600, 600));
                        emptyPanel.setLayout(new GridBagLayout());
                        JLabel label = new JLabel("𝙽𝙴𝚂𝚂𝚄𝙽𝙰 𝙼𝙾𝙽𝙴𝚃𝙰 𝚃𝚁𝙾𝚅𝙰");
                        label.setForeground(Color.WHITE);
                        label.setFont(new Font("SansSerif", Font.BOLD, 18));
                        emptyPanel.add(label);
                        JOptionPane.showMessageDialog(null, emptyPanel, "ᴍᴏɴᴇᴛᴇ ᴛʀᴏᴠᴀᴛᴇ", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
            
                    JPanel mainPanel = new JPanel();
                    mainPanel.setOpaque(true);
                    mainPanel.setBackground(new Color(101, 67, 33));
                    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
                    for (Coin c : validCoins) {
                        RoundedPanel coinPanel = new RoundedPanel(20, 20, new Color(255, 250, 230));
                        coinPanel.setLayout(new BorderLayout(20, 0));
                        coinPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
                        coinPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        coinPanel.setMaximumSize(new Dimension(650, 330));
            
                        // LEFT: Info testo
                        JPanel textPanel = new JPanel();
                        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                        textPanel.setOpaque(false);
                        textPanel.setAlignmentY(Component.TOP_ALIGNMENT);
                        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20)); 
            
                        String[] fields = {
                            "𝙸𝙳: " + c.getID(),
                            "𝙽𝚘𝚖𝚎: " + c.getName(),
                            "𝙰𝚗𝚗𝚘: " + c.getYear(),
                            "𝙿𝚛𝚎𝚣𝚣𝚘: €" + c.getPrice(),
                            "𝙼𝚊𝚝𝚎𝚛𝚒𝚊𝚕𝚎: " + c.getMaterial(),
                            "𝙿𝚎𝚜𝚘: " + c.getWeight() + " g",
                            "𝙳𝚒𝚊𝚖𝚎𝚝𝚛𝚘: " + c.getDiameter() + " mm",
                            "𝙰𝚕𝚝𝚎𝚣𝚣𝚊: " + c.getHeight() + " mm",
                            "𝙲𝚘𝚗𝚜𝚎𝚛𝚟𝚊𝚣𝚒𝚘𝚗𝚎 𝚏𝚛𝚘𝚗𝚝𝚎: " + c.getConservationObverse(),
                            "𝙲𝚘𝚗𝚜𝚎𝚛𝚟𝚊𝚣𝚒𝚘𝚗𝚎 𝚛𝚎𝚝𝚛𝚘: " + c.getConservationReverse(),
                            "𝙶𝚛𝚊𝚍𝚘 𝚗𝚞𝚖𝚒𝚜𝚖𝚊𝚝𝚒𝚌𝚘: " + c.getDegree(),
                            "𝙽𝚘𝚝𝚎: " + c.getNote()
                        };
            
                        for(String text : fields) {
                            JLabel row = new JLabel(text);
                            row.setForeground(new Color(50, 30, 10));
                            row.setAlignmentX(Component.LEFT_ALIGNMENT);
                            textPanel.add(row);
                        }
            
                        // RIGHT: immagini
                        JPanel imagesPanel = new JPanel();
                        imagesPanel.setLayout(new BoxLayout(imagesPanel, BoxLayout.Y_AXIS));
                        imagesPanel.setOpaque(false);
                        imagesPanel.setAlignmentY(Component.TOP_ALIGNMENT);
            
                        String obv = c.getPhotoPathObverse();
                        if (obv != null && !obv.isBlank()) {
                            imagesPanel.add(new JLabel("𝙵𝚛𝚘𝚗𝚝𝚎:"));
                            imagesPanel.add(Box.createVerticalStrut(4));
                            imagesPanel.add(new JLabel(createScaledIcon(obv)));
                        } else {
                            imagesPanel.add(new JLabel("𝙵𝚛𝚘𝚗𝚝𝚎: 𝚗𝚎𝚜𝚜𝚞𝚗𝚊 𝚒𝚖𝚖𝚊𝚐𝚒𝚗𝚎"));
                        }
            
                        imagesPanel.add(Box.createVerticalStrut(10));
            
                        String rev = c.getPhotoPathReverse();
                        if (rev != null && !rev.isBlank()) {
                            imagesPanel.add(new JLabel("𝚁𝚎𝚝𝚛𝚘:"));
                            imagesPanel.add(Box.createVerticalStrut(4));
                            imagesPanel.add(new JLabel(createScaledIcon(rev)));
                        } else {
                            imagesPanel.add(new JLabel("𝚁𝚎𝚝𝚛𝚘: 𝚗𝚎𝚜𝚜𝚞𝚗𝚊 𝚒𝚖𝚖𝚊𝚐𝚒𝚗𝚎"));
                        }
            
                        coinPanel.add(textPanel, BorderLayout.CENTER);
                        coinPanel.add(imagesPanel, BorderLayout.EAST);
            
                        JPanel wrapper = new JPanel();
                        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
                        wrapper.setOpaque(false);
                        wrapper.add(coinPanel);
                        wrapper.add(Box.createRigidArea(new Dimension(0, 20)));
            
                        mainPanel.add(wrapper);
                    }
            
                    JScrollPane scroll = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scroll.getVerticalScrollBar().setUnitIncrement(16);
                    scroll.setPreferredSize(new Dimension(700, 650));
            
                    JOptionPane.showMessageDialog(null, scroll, "ᴍᴏɴᴇᴛᴇ ᴛʀᴏᴠᴀᴛᴇ", JOptionPane.PLAIN_MESSAGE);
            
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "❌ 𝙴𝚁𝚁𝙾𝚁𝙴 𝙲𝙰𝚁𝙸𝙲𝙰𝙼𝙴𝙽𝚃𝙾: " + e.getMessage());
                }
            }
            
            case 3 -> {
                ImageIcon Icon2 = new ImageIcon(Menu.class.getClassLoader().getResource("assets/Icon2.png"));
            
                try {
                    List<Coin> coins = client.getAllCoins();
                    List<Coin> validCoins = coins.stream()
                        .filter(c -> c != null && c.getID() != null && !c.getID().isBlank())
                        .toList();
            
                    if (validCoins.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "❌ 𝙽𝙴𝚂𝚂𝚄𝙽𝙰 𝙼𝙾𝙽𝙴𝚃𝙰 𝙳𝙰 𝙴𝙻𝙸𝙼𝙸𝙽𝙰𝚁𝙴", "ʀɪᴍᴜᴏᴠɪ ᴍᴏɴᴇᴛᴀ", JOptionPane.INFORMATION_MESSAGE, Icon2);
                        return;
                    }
            
                    JPanel mainPanel = new JPanel();
                    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                    mainPanel.setBackground(new Color(255, 204, 204));
                    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            
                    List<JCheckBox> checkBoxes = new java.util.ArrayList<>();
                    for (Coin c : validCoins) {
                        JCheckBox box = new JCheckBox("𝙽𝙾𝙼𝙴: " + c.getName() + " —> 𝙸𝙳: " + c.getID());
                        box.setBackground(new Color(255, 204, 204));
                        checkBoxes.add(box);
                        mainPanel.add(box);
                    }
            
                    // Pannello con pulsanti
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
                    buttonPanel.setBackground(new Color(255, 204, 204));
            
                    JButton deleteSelected = new JButton("✅ 𝙴𝙻𝙸𝙼𝙸𝙽𝙰 𝚂𝙴𝙻𝙴𝚉𝙸𝙾𝙽𝙰𝚃𝙴");
                    JButton deleteAll = new JButton("𝙴𝙻𝙸𝙼𝙸𝙽𝙰 𝚃𝚄𝚃𝚃𝙴");
                    JButton cancel = new JButton("❌ 𝙰𝙽𝙽𝚄𝙻𝙻𝙰");
            
                    buttonPanel.add(deleteSelected);
                    buttonPanel.add(deleteAll);
                    buttonPanel.add(cancel);
            
                    JDialog dialog = new JDialog((Frame) null, "ʀɪᴍᴜᴏᴠɪ ᴍᴏɴᴇᴛᴀ", true);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.getContentPane().setLayout(new BorderLayout());
                    dialog.getContentPane().add(new JScrollPane(mainPanel), BorderLayout.CENTER);
                    dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
                    dialog.setSize(700, 600);
                    dialog.setLocationRelativeTo(null);
            
                    deleteSelected.addActionListener(e -> {
                        List<String> toDelete = new java.util.ArrayList<>();
                        for (int i = 0; i < checkBoxes.size(); i++) {
                            if (checkBoxes.get(i).isSelected()) {
                                toDelete.add(validCoins.get(i).getID());
                            }
                        }
            
                        if (toDelete.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog, "𝙽𝙴𝚂𝚂𝚄𝙽𝙰 𝙼𝙾𝙽𝙴𝚃𝙰 𝚂𝙴𝙻𝙴𝚉𝙸𝙾𝙽𝙰𝚃𝙰", "ʀɪᴍᴜᴏᴠɪ ᴍᴏɴᴇᴛᴀ", JOptionPane.WARNING_MESSAGE, Icon2);
                            return;
                        }
            
                        StringBuilder msg = new StringBuilder("✅ 𝙼𝙾𝙽𝙴𝚃𝙴 𝙴𝙻𝙸𝙼𝙸𝙽𝙰𝚃𝙴:\n");
                        for (String id : toDelete) {
                            try {
                                Coin deleted = client.deleteCoin(id);
                                msg.append("- ").append(deleted.getName()).append("\n");
                            } catch (IOException ex) {
                                msg.append("❌ 𝙴𝚁𝚁𝙾𝚁𝙴 𝙴𝙻𝙸𝙼𝙸𝙽𝙰𝚉𝙸𝙾𝙽𝙴 𝙸𝙳").append(id).append(": ").append(ex.getMessage()).append("\n");
                            }
                        }
            
                        JOptionPane.showMessageDialog(dialog, msg.toString(), "ʀɪᴍᴜᴏᴠɪ ᴍᴏɴᴇᴛᴀ", JOptionPane.INFORMATION_MESSAGE, Icon2);
                        dialog.dispose();
                    });
            
                    deleteAll.addActionListener(e -> {
                        int conferma = JOptionPane.showConfirmDialog(dialog, "𝚂𝙴𝙸 𝚂𝙸𝙲𝚄𝚁𝙾 𝙳𝙸 𝚅𝙾𝙻𝙴𝚁 𝙴𝙻𝙸𝙼𝙸𝙽𝙰𝚁𝙴 𝚃𝚄𝚃𝚃𝙴 𝙻𝙴 𝙼𝙾𝙽𝙴𝚃𝙴?", "ᴄᴏɴꜰᴇʀᴍᴀ ᴇʟɪᴍɪɴᴀᴢɪᴏɴᴇ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (conferma == JOptionPane.YES_OPTION) {
                            StringBuilder msg = new StringBuilder("✅ 𝙼𝙾𝙽𝙴𝚃𝙴 𝙴𝙻𝙸𝙼𝙸𝙽𝙰𝚃𝙴:\n");
                            for (Coin c : validCoins) {
                                try {
                                    Coin deleted = client.deleteCoin(c.getID());
                                    msg.append("- ").append(deleted.getName()).append("\n");
                                } catch (IOException ex) {
                                    msg.append("❌ 𝙴𝚁𝚁𝙾𝚁𝙴 𝙴𝙻𝙸𝙼𝙸𝙽𝙰𝚉𝙸𝙾𝙽𝙴 𝙸𝙳 ").append(c.getID()).append(": ").append(ex.getMessage()).append("\n");
                                }
                            }
            
                            JOptionPane.showMessageDialog(dialog, msg.toString(), "ʀɪᴍᴜᴏᴠɪ ᴍᴏɴᴇᴛᴀ", JOptionPane.INFORMATION_MESSAGE, Icon2);
                            dialog.dispose();
                        }
                    });
            
                    cancel.addActionListener(e -> dialog.dispose());
            
                    dialog.setVisible(true);
            
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "❌ 𝙴𝚁𝚁𝙾𝚁𝙴 𝙲𝙰𝚁𝙸𝙲𝙰𝙼𝙴𝙽𝚃𝙾 𝙼𝙾𝙽𝙴𝚃𝙴: " + e.getMessage(), "ʀɪᴍᴜᴏᴠɪ ᴍᴏɴᴇᴛᴀ", JOptionPane.ERROR_MESSAGE, Icon2);
                }
            }

            case 4 -> {
                try {
                    GoldPriceClient goldClient = new GoldPriceClient();
                    var gold = goldClient.getGoldPrice("EUR");
                    JOptionPane.showMessageDialog(null, "📈 Prezzo Oro\n\n𝟚𝟜𝕂: €" + gold.price_gram_24k + "\n𝟚𝟚𝕂: €" + gold.price_gram_22k);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "❌ ᴇʀʀᴏʀᴇ ᴀᴄᴄᴇꜱꜱᴏ ᴏʀᴏ: " + e.getMessage());
                }
            }

            default -> JOptionPane.showMessageDialog(null, "ꜱᴄᴇʟᴛᴀ ɴᴏɴ ᴠᴀʟɪᴅᴀ");
        }
    }

    private static final int IMG_WIDTH  = 120;
    private static final int IMG_HEIGHT = 120;
    
    private static ImageIcon createScaledIcon(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image thumb = img.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
            return new ImageIcon(thumb);
        } catch (Exception e) {
            return new ImageIcon(path);
        }
    }

};

class RoundedPanel extends JPanel {
    private final int arcWidth;
    private final int arcHeight;
    private final Color backgroundColor;

    public RoundedPanel(int arcWidth, int arcHeight, Color backgroundColor) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(arcWidth, arcHeight);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(backgroundColor);
        graphics.fillRoundRect(0, 0, getWidth(), getHeight(), arcs.width, arcs.height);
    }

};
