package com.aspect.oriented;

import java.util.Objects;

public class Account {

    private String name;
    private String level;

    // Default constructor
    public Account() {}

    // Parameterized constructor
    public Account(String name, String level) {
        this.name = name;
        this.level = level;
    }

    // Builder pattern for flexible object creation
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String level;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder level(String level) {
            this.level = level;
            return this;
        }

        public Account build() {
            return new Account(name, level);
        }
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validate(name, "Name");
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = validate(level, "Level");
    }

    // Validation helper
    private String validate(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " cannot be null or empty");
        }
        return value;
    }

    // equals & hashCode for object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) &&
               Objects.equals(level, account.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }

    // toString override
    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
