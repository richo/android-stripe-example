package net.psych0tik.stripe_example;

import android.app.Activity;
import android.os.Bundle;

import java.util.Map;
import java.util.HashMap;

// Stripe
import com.stripe.model.Charge;
import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.CardException;
import com.stripe.exception.APIException;
//

import android.webkit.WebView;
import android.content.Intent;
import android.net.Uri;

public class debug extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void runTestWebView() {
        WebView webview = new WebView(this);
        setContentView(webview);

        // Allow following links. I have no idea how or why this works ¯\_(ツ)_/¯
        webview.setWebViewClient(new WebViewClient());

        // Enable the javascripts
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.loadUrl("http://example.org");
    }

    public void runTestCharge() {
        Map<String, Object> card = testCard();
        try {
            testCharge(card);
        } catch(AuthenticationException e) {
            System.out.println("AuthenticationException");
        } catch(InvalidRequestException e) {
            System.out.println("InvalidRequestException");
        } catch(APIConnectionException e) {
            System.out.println("APIConnectionException");
        } catch(CardException e) {
            System.out.println("CardException");
        } catch(APIException e) {
            System.out.println("APIException");
        }
    }

    private void testCharge(Map<String, Object> card) throws AuthenticationException, InvalidRequestException,
           APIConnectionException, CardException, APIException  {
       Stripe.apiKey = "tGN0bIwXnHdwOa85VABjPdSn8nWY7G7I"; // stripe public
       // test key

       final Map<String, Object> chargeParams = new HashMap<String, Object>();
       chargeParams.put("amount", 100);
       chargeParams.put("currency", "usd");
       chargeParams.put("card", card);

       final Charge charge = Charge.create(chargeParams);
       System.out.println(charge);
    }

    private Map<String, Object> testCard() {
        final Map<String, Object> card = new HashMap<String, Object>();
        card.put("number", "4242424242424242");
        card.put("exp_month", 12);
        card.put("exp_year", 2015);
        card.put("cvc", "123");
        card.put("name", "J Bindings Cardholder");
        card.put("address_line1", "140 2nd Street");
        card.put("address_line2", "4th Floor");
        card.put("address_city", "San Francisco");
        card.put("address_zip", "94105");
        card.put("address_state", "CA");
        card.put("address_country", "USA");
        return card;
    }
}
