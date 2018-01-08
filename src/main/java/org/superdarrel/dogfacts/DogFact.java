package org.superdarrel.dogfacts;

public class DogFact {
    private String [] facts;
    private boolean success;

    public String[] getFacts() {
        return facts;
    }

    public void setFacts(String[] fact) {
        this.facts = fact;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
