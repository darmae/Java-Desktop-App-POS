package enums;

public enum UserTypes {

	STRING_ADMIN("Administrator", 1),
    STRING_USER("Cashier", 2);

    private final String text;
    private final int key;

    /**
     * @param text
     */
    private UserTypes(final String text, int id) {
        this.text = text;
        this.key = id;
    }
    
    @Override
    public String toString() {
        return text;
    }
    
    public int getKey(){
    	return key;
    }
}
