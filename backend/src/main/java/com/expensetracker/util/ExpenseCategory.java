package com.expensetracker.util;

public enum ExpenseCategory {
    FOOD("Food"),
    TRANSPORTATION("Transportation"),
    ENTERTAINMENT("Entertainment"),
    HEALTHCARE("Healthcare"),
    SHOPPING("Shopping"),
    UTILITIES("Utilities"),
    OTHER("Other");

    private final String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ExpenseCategory fromString(String category) {
        for (ExpenseCategory c : ExpenseCategory.values()) {
            if (c.displayName.equalsIgnoreCase(category)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No enum constant for category: " + category);
    }
}