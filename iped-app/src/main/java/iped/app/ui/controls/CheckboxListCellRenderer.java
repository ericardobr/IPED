package iped.app.ui.controls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.function.Predicate;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class CheckboxListCellRenderer<E> extends JPanel implements ListCellRenderer<E> {
    JCheckBox enabledCheckBox = new JCheckBox();
    Predicate isEnabled;
    int maxStringWidth = 0;
    boolean indexedPredicate = false;

    public CheckboxListCellRenderer(Predicate isEnabled) {
        this.setLayout(new BorderLayout());
        this.isEnabled = isEnabled;
        this.setBackground(Color.white);
        add(enabledCheckBox, BorderLayout.CENTER);
    }

    public CheckboxListCellRenderer(Predicate isEnabled, boolean indexedPredicate) {
        this(isEnabled);
        this.indexedPredicate = indexedPredicate;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected,
            boolean cellHasFocus) {
        if (indexedPredicate) {
            enabledCheckBox.setSelected(isEnabled.test(index));
        } else {
            enabledCheckBox.setSelected(isEnabled.test(value));
        }
        enabledCheckBox.setText(value.toString());

        this.add(enabledCheckBox, BorderLayout.CENTER);
        if (isSelected) {
            this.setBackground(Color.BLUE);
            enabledCheckBox.setForeground(Color.white);
        } else {
            this.setBackground(Color.white);
            enabledCheckBox.setForeground(Color.black);
        }

        return this;
    }

    public int getMaxStringWidth() {
        if (maxStringWidth < enabledCheckBox.getWidth()) {
            maxStringWidth = enabledCheckBox.getWidth();
        }
        return maxStringWidth;
    }

    public void addCheckBoxActionListener(ActionListener l) {
        enabledCheckBox.addActionListener(l);
    }
}