package mate.academy.internetshop.factory;

public class IdGenerator {

    private static Long idGenerator = 1L;

    private IdGenerator() {
    }

    public static Long getGeneratedId() {
        return idGenerator++;
    }
}
