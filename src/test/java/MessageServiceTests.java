import com.tcs.edu.domain.Message;
import com.tcs.edu.exceptions.LogException;
import com.tcs.edu.ifaces.MessageService;
import com.tcs.edu.printer.DecoratingMessageService;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

import static com.tcs.edu.constants.Severity.*;

public class MessageServiceTests {

    private MessageService service = null;

    @BeforeEach
    public void beforeTest() {
        service = new DecoratingMessageService();
    }

    @AfterEach
    void afterTest() { service = null; }

    @Test
    public void shouldReturnAllMessagesAfterFindAllMethod() {

        service.create(new Message(REGULAR, "Hello world!"),
                new Message(REGULAR, "Hello world!"),
                new Message(REGULAR, "Hello world!"),
                new Message(REGULAR, "Hello world!"));


        assertThat(service.findAll().size()).isEqualTo(4);
    }

    @Test
    public void shouldReturnMessageBySeverity() {

        service.create(new Message(REGULAR, "Hello world!"),
                       new Message(MAJOR, "Hello world!"),
                       new Message(MINOR, "Hello world!"),
                       new Message(REGULAR, "Hello world!"));

        assertThat(service.findBySeverity(REGULAR).size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnMessageByPrimaryKey() {

        Message m1 = new Message(REGULAR, "Hello world!");
        service.create(m1);

        assertThat(service.findByPrimaryKey(m1.getId())).isEqualTo(m1);
    }

    @Test
    public void shouldReturnLogExceptionWhenMessageIsNull() {
        Exception exception = Assertions.assertThrows(LogException.class, () -> service.create(null));
        assertThat(exception.getMessage()).isEqualTo("Error argument message. Parameters message is null.");
    }



    @Nested
    @DisplayName("Tests for check size" +
            "")
    class CheckSizeTest {

        private MessageService service = null;
        Message m1 = new Message(REGULAR, "I am first");
        Message m2 = new Message(REGULAR, "I am second");
        Message m3 = new Message(REGULAR, "I am third");

        @BeforeEach
        public void beforeTest() {
            service = new DecoratingMessageService();
        }

        @AfterEach
        void afterTest() { service = null; }

        @Test
        @DisplayName("Size is equal 1")
        void checkSizeIsEqualOneElementTest() {
            service.create(m1);
            assertThat(service.findAll().size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Size is equal 2")
        void checkSizeIsEqualTwoElementTest() {
            service.create(m1, m2);
            assertThat(service.findAll().size()).isEqualTo(2);
        }

        @Test
        @DisplayName("Size is equal 3")
        void checkSizeIsEqualThreeElementTest() {
            service.create(m1, m2, m3);
            assertThat(service.findAll().size()).isEqualTo(3);
        }
    }
}
