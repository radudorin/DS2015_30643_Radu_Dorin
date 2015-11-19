package GUI;

import RMIClient.RMIClient;
import model.Car;

public class UI extends javax.swing.JFrame {

	/**
	 * Creates new form NewJFrame
	 */
	public UI() {
		initComponents();
	}

	private void initComponents() {

		priceLabel = new javax.swing.JLabel();
		engineCapacityEditText = new javax.swing.JTextField();
		yearEditText = new javax.swing.JTextField();
		priceEditText = new javax.swing.JTextField();
		engineCapLabel = new javax.swing.JLabel();
		yearLabel = new javax.swing.JLabel();
		errorLabel = new javax.swing.JLabel();
		calculateTaxButton = new javax.swing.JButton();
		calculatePriceButton = new javax.swing.JButton();
		taxTextField = new javax.swing.JTextField();
		priceTextField = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		priceLabel.setText("Price");

		engineCapacityEditText.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				engineCapacityEditTextActionPerformed(evt);
			}
		});

		yearEditText.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				yearEditTextActionPerformed(evt);
			}
		});

		priceEditText.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				priceEditTextActionPerformed(evt);
			}
		});

		engineCapLabel.setText("Engine capacity");

		yearLabel.setText("Year");

		errorLabel.setForeground(new java.awt.Color(255, 51, 51));

		calculateTaxButton.setText("Compute Tax");
		calculateTaxButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				calculateTaxButtonActionPerformed(evt);
			}
		});

		calculatePriceButton.setText("Compute Price");
		calculatePriceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				calculatePriceButtonActionPerformed(evt);
			}
		});

		taxTextField.setEditable(false);
		taxTextField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				taxTextFieldActionPerformed(evt);
			}
		});

		priceTextField.setEditable(false);
		priceTextField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				priceTextFieldActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(errorLabel, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(priceLabel).addComponent(yearLabel).addComponent(engineCapLabel))
								.addGap(151, 151, 151)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(engineCapacityEditText, javax.swing.GroupLayout.PREFERRED_SIZE,
												252, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(yearEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 252,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(priceEditText, javax.swing.GroupLayout.PREFERRED_SIZE, 252,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(calculateTaxButton, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(calculatePriceButton, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(priceTextField, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 252,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(taxTextField, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 252,
												javax.swing.GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(161, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(priceLabel).addComponent(priceEditText, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(31, 31, 31)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(engineCapacityEditText, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(engineCapLabel))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(yearEditText, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(yearLabel))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(errorLabel)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(calculatePriceButton).addComponent(priceTextField,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(25, 25, 25).addComponent(calculateTaxButton))
						.addGroup(layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(taxTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(168, Short.MAX_VALUE)));

		pack();
	}

	private void priceEditTextActionPerformed(java.awt.event.ActionEvent evt) {
		errorLabel.setText("");
	}

	private void engineCapacityEditTextActionPerformed(java.awt.event.ActionEvent evt) {
		errorLabel.setText("");
	}

	private void yearEditTextActionPerformed(java.awt.event.ActionEvent evt) {
		errorLabel.setText("");
	}

	private void calculatePriceButtonActionPerformed(java.awt.event.ActionEvent evt) {

		Car car = getCar();

		if (car == null) {
			return;
		}

		try {
			priceTextField.setText(RMIClient.computePrice(car));
		} catch (Exception e) {
			errorLabel.setText("Error during computing");
		}

	}

	private void calculateTaxButtonActionPerformed(java.awt.event.ActionEvent evt) {

		Car car = getCar();

		if (car == null) {
			return;
		}

		try {
			taxTextField.setText(RMIClient.computeTax(car));
		} catch (Exception e) {
			errorLabel.setText("Error during computing");
		}
	}

	private void taxTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void priceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private Car getCar() {
		try {
			double price = Double.parseDouble(priceEditText.getText().toString());
			int capacity = Integer.parseInt(engineCapacityEditText.getText().toString());
			int year = Integer.parseInt(yearEditText.getText().toString());

			return new Car(price, capacity, year);
		} catch (Exception e) {
			errorLabel.setText("Please enter valid data");
			priceEditText.setText("");
			yearEditText.setText("");
			engineCapacityEditText.setText("");
			return null;
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UI().setVisible(true);
			}
		});
	}

	private javax.swing.JButton calculatePriceButton;
	private javax.swing.JButton calculateTaxButton;
	private javax.swing.JLabel engineCapLabel;
	private javax.swing.JTextField engineCapacityEditText;
	private javax.swing.JLabel errorLabel;
	private javax.swing.JTextField priceEditText;
	private javax.swing.JLabel priceLabel;
	private javax.swing.JTextField priceTextField;
	private javax.swing.JTextField taxTextField;
	private javax.swing.JTextField yearEditText;
	private javax.swing.JLabel yearLabel;
}
