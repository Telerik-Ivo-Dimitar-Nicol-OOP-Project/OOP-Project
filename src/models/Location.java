package models;

public enum Location {
    SYD, MEL, ADL, ASP, BRI, DAR, PER;
    @Override
    public String toString() {
        switch (this) {
            case SYD:
                return "SYD";
            case MEL:
                return "MEL";
            case ADL:
                return "ADL";
            case ASP:
                return "ASP";
            case BRI:
                return "BRI";
            case DAR:
                return "DAR";
            case PER:
                return "PER";

            default:
                throw new IllegalArgumentException();
        }
    }
}
