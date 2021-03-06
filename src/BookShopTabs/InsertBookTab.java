package BookShopTabs;

import BookShopGui.BookShopTab;
import BookShopLogic.Book;
import BookShopLogic.BookQueries;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertBookTab extends BookShopTab {

  //local variables
  private JTextField bookNameInput;
  private JTextField bookAuthorInput;
  private JTextField bookPriceInput;

  /**
   * Constructor for the insert book tab
   */
  public InsertBookTab(){
    //create main GUI elements
    this.setLabel("Insert Book");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    JPanel subPanel = new JPanel(new FlowLayout());
    subPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
    JButton submitButton = new JButton("Add New Book");
    submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    //add listener to submit button
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        insertBook();
      }
    });

    int textFieldWidth = 250;
    int textFieldHeight = 30;
    //create input fields
    bookNameInput = new JTextField();
    bookNameInput.setPreferredSize(new Dimension(textFieldWidth,textFieldHeight));
    JPanel bookNamePanel = new JPanel(new BorderLayout());
    bookNamePanel.setBorder(new TitledBorder("Book Name"));
    bookNamePanel.add(bookNameInput);

    bookAuthorInput = new JTextField();
    bookAuthorInput.setPreferredSize(new Dimension(textFieldWidth,textFieldHeight));
    JPanel bookAuthorPanel = new JPanel(new BorderLayout());
    bookAuthorPanel.setBorder(new TitledBorder("Author Name"));
    bookAuthorPanel.add(bookAuthorInput);

    bookPriceInput = new JTextField();
    bookPriceInput.setPreferredSize(new Dimension(textFieldWidth,textFieldHeight));
    JPanel bookPricePanel = new JPanel(new BorderLayout());
    bookPricePanel.setBorder(new TitledBorder("Price"));
    bookPricePanel.add(bookPriceInput);

    //add GUI elements to containers
    subPanel.add(bookNamePanel);
    subPanel.add(bookAuthorPanel);
    subPanel.add(bookPricePanel);
    panel.add(subPanel);
    panel.add(submitButton);

    this.add(panel, BorderLayout.CENTER);

  }

  /**
   * Submit button listener to insert book
   */
  private void insertBook(){
    String bookName = bookNameInput.getText();
    String bookAuthor = bookAuthorInput.getText();
    String stringBookPrice = bookPriceInput.getText();
    if (bookName.isEmpty() || bookAuthor.isEmpty() || stringBookPrice.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Please fill out all fields", "Insufficient Information", JOptionPane.ERROR_MESSAGE);
    } else {
      try {
        double bookPrice = Double.parseDouble(stringBookPrice);
        Book newBook = new Book(bookName, bookAuthor, bookPrice);
        BookQueries.insertBook(newBook);
        bookNameInput.setText("");
        bookAuthorInput.setText("");
        bookPriceInput.setText("");
        JOptionPane.showMessageDialog(null, "Book Successfully Added", "Success", JOptionPane.INFORMATION_MESSAGE);
      } catch(NumberFormatException exception){
        JOptionPane.showMessageDialog(null, "Please enter a valid number for price", "Invalid Input", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}