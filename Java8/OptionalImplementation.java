package Java8;

import java.util.Optional;

/**
 * Optional class can be used to represent a situation where an object of type T may or may not be present(not null).
 */
public class OptionalImplementation {

    public static void main(String[] args) {

        //when using Optional.of, we have to be sure that user modal is not null
        //else NPE would be thrown
        Optional<UserModel> nonNullOptional = Optional.of(new UserModel(1, "Sahil"));
        printDetailsOfOptional(nonNullOptional);

        //better version of method to create an instance of Optional is following
        //here instance of UserModel can be null
        //when instance of UserModel is empty, then Optional class returns empty instance which does not contain null (Optional.empty())
        //else returns the optional class instance which would be containing the  instance of UserModel instance
        Optional<UserModel> nullableOptional = Optional.ofNullable(new UserModel(2, "Gupta"));
        printDetailsOfOptional(nullableOptional);

        //we can use ofNullable function to create empty optionals
        //throws NoSuchElementException is instance is absent.
        Optional<UserModel> emptyOptional = Optional.ofNullable(null);
        printDetailsOfOptional(emptyOptional);

    }

    private static void printDetailsOfOptional(Optional<UserModel> nullableOptional) {

        System.out.println("is present: " + nullableOptional.isPresent());
        if (nullableOptional.isPresent()) {

            System.out.println("user details: " + nullableOptional.get().toString());
        }
    }

    private static class UserModel {

        private final long mUserId;
        private final String mUsername;

        public UserModel(long userId, String username) {
            mUserId = userId;
            mUsername = username;
        }

        public long getUserId() {
            return mUserId;
        }

        public String getUsername() {
            return mUsername;
        }

        @Override
        public String toString() {
            return "UserModel{" +
                    "mUserId=" + mUserId +
                    ", mUsername='" + mUsername + '\'' +
                    '}';
        }
    }
}
