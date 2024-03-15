package eduCourse_prj.admin;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminAdminMgtEvent implements ActionListener {
    AdminAdminMgtDesign aamd;

    public AdminAdminMgtEvent(AdminAdminMgtDesign aamd) {
        this.aamd = aamd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            JPanel parentPanel = (JPanel) clickedButton.getParent();

            if (aamd.outerPanel1List.contains(parentPanel)) {
                aamd.outerPanel2List.add(parentPanel);
                clickedButton.setText("취소");

                aamd.outerPanel1List.remove(parentPanel);
                aamd.outerPanel1.remove(parentPanel);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = aamd.outerPanel2List.size() - 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1.0;
                gbc.insets = new Insets(5, 5, 5, 5);
                aamd.outerPanel2.add(parentPanel, gbc);
            } else if (aamd.outerPanel2List.contains(parentPanel)) {
                aamd.outerPanel1List.add(parentPanel);
                clickedButton.setText("담기");

                aamd.outerPanel2List.remove(parentPanel);
                aamd.outerPanel2.remove(parentPanel);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = aamd.outerPanel1List.size() - 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.weightx = 1.0;
                gbc.insets = new Insets(5, 5, 5, 5);
                aamd.outerPanel1.add(parentPanel, gbc);
            }

            aamd.revalidate();
            aamd.repaint();
        }
    }
}