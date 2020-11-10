package java.base.a7optional;

import java.util.Optional;

public class OptionalUtily {
    public static Optional<Integer> stringToInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException ex){
            return Optional.empty();
        }
    }
}
