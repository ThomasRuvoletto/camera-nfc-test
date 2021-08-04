package com.example.cameranfc.nfc;

import android.app.Activity;
import android.nfc.NfcAdapter;
import android.widget.Toast;

public class NFCAdapter implements NfcAdapter.ReaderCallback {
    private final Activity context;

    public NFCAdapter(Activity context) {
        this.context = context;
    }

    public void EnableReaderMode() {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this.context);
        if (nfcAdapter != null) {
            nfcAdapter.enableReaderMode(this.context, this, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_NFC_B | NfcAdapter.FLAG_READER_NFC_BARCODE | NfcAdapter.FLAG_READER_NFC_F | NfcAdapter.FLAG_READER_NFC_V | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null);
        }
    }

    @Override
    public void onTagDiscovered(android.nfc.Tag tag) {
        this.context.runOnUiThread(() -> {
            Toast toast = Toast.makeText(this.context, "Tag detected", Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}