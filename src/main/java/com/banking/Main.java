package com.banking;

import com.banking.repository.DatabaseInitializer;
import com.banking.view.View;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        View.getInstance().run();
    }
}
