package mate.acadamy.internetshop.factory;

public class IdGenerator {

    private static Long idGenerator = 0L;

    private IdGenerator() {
    }

    public static Long getGeneratedId() {
        return idGenerator++;
    }
}
