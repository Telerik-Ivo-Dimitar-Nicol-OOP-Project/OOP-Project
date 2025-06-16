package models;

public enum Location {
    SYD, MEL, ADL, ASP, BRI, DAR, PER;
    @Override
    public String toString() {
        switch (this) {
            case SYD:
                return "Sydney";
            case MEL:
                return "Melbourne";
            case ADL:
                return "ADELAIDE";
            case ASP:
                return "Alice Springs";
            case BRI:
                return "Brisbane";
            case DAR:
                return "Darwin";
            case PER:
                return "Perth";

            default:
                throw new IllegalArgumentException();
        }
    }
}
