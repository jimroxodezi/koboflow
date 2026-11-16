package ng.koboflow.common.money;

public record Money(long kobo) implements Comparable<Money> {
    public static final Money ZERO = new Money(0L);

    public Money add(Money other) {
        return new Money(this.kobo + other.kobo);
    }

    public Money subtract(Money other) {
        return new Money(this.kobo - other.kobo);
    }

    @Override
    public int compareTo(Money other) {
        return Long.compare(this.kobo, other.kobo);
    }
}