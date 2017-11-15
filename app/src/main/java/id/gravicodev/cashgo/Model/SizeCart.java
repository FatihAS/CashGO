package id.gravicodev.cashgo.Model;

public class SizeCart {

    private String size;
    private boolean isSelected = false;

    public SizeCart(String size, boolean isSelected) {
        this.size = size;
        this.isSelected = isSelected;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
