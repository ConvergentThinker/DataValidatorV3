import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SampleTools {
    private void createAndDisplayGui() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createSampledetailsPanel());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private JPanel createSampledetailsPanel() {
        JPanel sampledetailsPanel = new JPanel(new GridBagLayout());
        sampledetailsPanel.setPreferredSize(new Dimension(500, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.left = 10;
        gbc.insets.top = 10;
        JLabel nameLabel = new JLabel("Sample Name");
        nameLabel.setBackground(Color.BLUE);
        sampledetailsPanel.add(nameLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        JLabel fileLabel = new JLabel("Sample File");
        sampledetailsPanel.add(fileLabel, gbc);
        return sampledetailsPanel;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new SampleTools().createAndDisplayGui());
    }
}
