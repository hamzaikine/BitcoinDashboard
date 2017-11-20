
package com.hamzaikine.bitcoindashboard;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hamzaikine
 */
public class Address {
    private String hash160;
    private String address;
    private long totalReceived;
    private long totalSent;
    private long finalBalance;
    private int txCount;
    private List<Transaction> transactions;

    public Address (String hash160, String address, long totalReceived, long totalSent, long finalBalance, int txCount, List<Transaction> transactions) {
        this.hash160 = hash160;
        this.address = address;
        this.totalReceived = totalReceived;
        this.totalSent = totalSent;
        this.finalBalance = finalBalance;
        this.txCount = txCount;
        this.transactions = transactions;
    }

    public Address (JsonObject a) {

        this(
                a.has("hash160") ? a.get("hash160").getAsString() : "",
                a.has("address") ? a.get("address").getAsString() : "",
                a.has("total_received") ? a.get("total_received").getAsLong() : 0,
                a.has("total_sent") ? a.get("total_sent").getAsLong() : 0,
                a.has("final_balance") ? a.get("final_balance").getAsLong() : 0,
                a.has("n_tx") ? a.get("n_tx").getAsInt() : 0,
                null);

        transactions = new ArrayList<Transaction>();
        for (JsonElement txElem : a.get("txs").getAsJsonArray()) {
            JsonObject addrObj = txElem.getAsJsonObject();
            transactions.add(new Transaction(addrObj));
        }
    }
    
    
    
}