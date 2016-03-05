package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity = 2;
    int price = 5;
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+ custName());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price) );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        //displayMessage(createOrderSummary(price));

    }
    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price = 5;
        if (hasWhippedCream()){
            price =price + 1;
        }
        if (hasChocolate()){
            price =price + 2;
        }
        return quantity * price;
    }
    /**
     * This method increments the order by 1
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "No more than 100 coffees";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
    }
    /**
     * This method decrements the order by 1
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Must Order at least 1 coffee";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int amount) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + amount);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method will create a summary of the order, it includes name, quantity ordered
     * total price and a message
     */
    private String createOrderSummary(int total) {
        String priceMessage ="Name: "+custName() + "\nAdd Whipped Cream? " + hasWhippedCream() + "\nAdd Chocolate? " + hasChocolate() + "\nQuantity: " + quantity + "\nTotal: $" + (total);
        priceMessage=priceMessage + "\nThank You!";
        return priceMessage;
    }
    private boolean hasWhippedCream() {
        CheckBox checked = (CheckBox) findViewById(R.id.whippedCreamBox);
        return checked.isChecked();
    }

    private boolean hasChocolate() {
        CheckBox checked = (CheckBox) findViewById(R.id.chocolateBox);
        return checked.isChecked();
    }
    private String custName() {
        EditText nameBox = (EditText) findViewById(R.id.nameBox);
        return nameBox.getText().toString();
    }
}