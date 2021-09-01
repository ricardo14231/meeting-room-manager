package dio.meeting.room.message;

public class MessageResponse {

    public static String messageObjCreate(Long id, String typeObj) {
        return String.format("%s salvo(a) com ID: %o!", typeObj, id);
    }

    public static String messageObjUpdate(Long id, String typeObj) {
        return String.format("%s com ID: %o atualizado(a)!", typeObj, id);
    }

    public static String messageObjDelete(Long id, String typeObj) {
        return String.format("%s com ID: %o deletado(a)!", typeObj, id);
    }
}
