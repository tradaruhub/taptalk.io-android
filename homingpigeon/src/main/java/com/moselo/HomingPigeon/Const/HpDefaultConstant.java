package com.moselo.HomingPigeon.Const;

public class HpDefaultConstant {

    public static final class RoomDatabase {
        public static final int kDatabaseVersion = 1;
    }

    public static final class ConnectionEvent {
        public static final String kEventOpenRoom = "chat/openRoom";
        public static final String kSocketCloseRoom = "chat/closeRoom";
        public static final String kSocketNewMessage = "chat/sendMessage";
        public static final String kSocketUpdateMessage = "chat/updateMessage";
        public static final String kSocketDeleteMessage = "chat/deleteMessage";
        public static final String kSocketOpenMessage = "chat/openMessage";
        public static final String kSocketStartTyping = "chat/startTyping";
        public static final String kSocketStopTyping = "chat/stopTyping";
        public static final String kSocketAuthentication = "user/authentication";
        public static final String kSocketUserOnline = "user/online";
        public static final String kSocketUserOffline = "user/offline";
    }

    public static final class DatabaseType {
        public static final String MESSAGE_DB = "MessageDB";
        public static final String SEARCH_DB = "SearchDB";
        public static final String MY_CONTACT_DB = "MyContactDB";
    }

    public static final class MessageType {
        public static final int TYPE_TEXT = 1001;
        public static final int TYPE_IMAGE = 1002;
        public static final int TYPE_VIDEO = 1003;
        public static final int TYPE_FILE = 1004;
        public static final int TYPE_LOCATION = 1005;
        public static final int TYPE_CONTACT = 1006;
        public static final int TYPE_STICKER = 1007;

        //public static final int TYPE_NEW_USER = 1000;
        //public static final int TYPE_USER_LEAVE = 1001;
        //public static final int TYPE_SEE_SERVICES_N_PRICES = 8001;
        //public static final int TYPE_WELCOME = 8006;
        //public static final int TYPE_ASK_PRODUCT = 9007;

        public static final int TYPE_PRODUCT = 2001;
        public static final int TYPE_CATEGORY = 2002;
        public static final int TYPE_ORDER_CARD = 2003;
        public static final int TYPE_CONFIRM_MY_PAYMENT = 2004;

        public static final int TYPE_HIDDEN = 0x9196;
    }

    public static final class BubbleType {
        public static final int TYPE_LOG = 0;
        public static final int TYPE_BUBBLE_TEXT_RIGHT = 1001;
        public static final int TYPE_BUBBLE_TEXT_LEFT = 1002;
        public static final int TYPE_BUBBLE_IMAGE_RIGHT = 1011;
        public static final int TYPE_BUBBLE_IMAGE_LEFT = 1012;

        public static final int TYPE_BUBBLE_PRODUCT_LIST = 3001;
    }

    public static final class Extras {
        public static final String ROOM_NAME = "roomName";
        public static final String MY_ID = "myID";
        public static final String GROUP_MEMBERS = "groupMembers";
        public static final String GROUP_NAME = "kGroupName";
        public static final String GROUP_IMAGE = "kGroupImage";
    }

    public static final class RequestCode {
        public static final int CREATE_GROUP = 10;
        public static final int PICK_GROUP_IMAGE = 11;
        public static final int SEND_IMAGE_FROM_CAMERA = 12;
        public static final int SEND_IMAGE_FROM_GALLERY = 13;
    }

    public static final class PermissionRequest {
        public static final int PERMISSION_CAMERA = 1;
        public static final int PERMISSION_READ_EXTERNAL_STORAGE = 2;
        public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 3;
    }

    public static final class Sorting {
        public static final int DESCENDING = 1;
        public static final int ASCENDING = 2;
    }

    public static final class HttpResponseStatusCode {
        public static final int RESPONSE_SUCCESS = 200;
        public static final int RESPONSE_EMPTY = 204;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int TO_MANY_REQUEST = 429;
        public static final int APP_KEY_INVALID = 491;
        public static final int APP_NOT_AVAILABLE = 492;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }

    public static final class Notification {
        public static final String K_FIREBASE_TOKEN = "kFirebaseToken";
        public static final String K_TEXT_REPLY = "kTextReply";
        public static final String K_NOTIFICATION_MESSAGE_MAP = "kNotificationMessageMap";
        public static final int K_REPLY_REQ_CODE = 1;
    }

    public static final class ApiErrorCode {
        public static final int HTTP_HEADER_VALIDATION_FAILED = 40001;
        public static final int API_PARAMETER_VALIDATION_FAILED = 40002;
        public static final int SERVER_KEY_NOT_PROVIDED = 49101;
        public static final int INVALID_SERVER_KEY = 49102;
        public static final int SERVER_KEY_NOT_FOUND = 49103;
        public static final int SERVER_KEY_INCOMPATIBLE = 49104;
        public static final int SERVER_KEY_EXPIRED = 49106;
        public static final int SERVER_KEY_NOT_ENABLED_BY_CUSTOMER = 49201;
        public static final int SERVER_NOT_AVAILABLE = 49202;
        public static final int ERROR_VALIDATING_SERVER_KEY = 50001;
        public static final int OTHER_ERRORS = 99999;
    }

    public static final class TokenHeaderConst {
        public static final int NOT_USE_REFRESH_TOKEN = 1;
        public static final int USE_REFRESH_TOKEN = 2;
    }

    public static final class OldDataConst {
        public static final String K_LAST_DELETE_TIMESTAMP = "kLastDeleteTimestamp";
    }

    public static final String K_REFRESH_TOKEN = "kRefreshToken";
    public static final String K_REFRESH_TOKEN_EXPIRY = "kRefreshTokenExpiry";
    public static final String K_ACCESS_TOKEN = "kAccessToken";
    public static final String K_ACCESS_TOKEN_EXPIRY = "kAccessTokenExpiry";
    public static final String K_AUTH_TICKET = "kAuthTicket";
    public static final String K_MY_USERNAME = "kMyUsername";
    public static final String K_USER = "kUser";
    public static final String K_RECIPIENT_ID = "kRecipientID";
    public static final String K_ROOM = "kRoom";
    public static final String K_LAST_UPDATED = "kLastUpdated";
    public static final String K_IS_ROOM_LIST_SETUP_FINISHED = "kIsRoomListSetupFinished";
    public static final String ENCRYPTION_KEY = "kHT0sVGIKKpnlJE5BNkINYtuf19u6+Kk811iMuWQ5tM";
    public static final String DB_ENCRYPT_PASS = "MoseloOlesom";

    public static final int NUM_OF_ITEM = 50;
    public static final int GROUP_MEMBER_LIMIT = 50;
    public static final int DEFAULT_ANIMATION_TIME = 200;

    public static final String APP_KEY_ID = "b43b48745dfa0e44k1";
    public static final String APP_KEY_SECRET = "MzI5XzEuMV/9hcHBfa2V5X2lkX2FuZD/oxNTM2OTk3ODc3MjI0NzI4";

    public static final String SCAN_RESULT = "kScanResult";
    public static final String ADDED_CONTACT = "kAddedContact";

    public static final int CLOSE_FOR_RECONNECT_CODE = 666;

    public static final String HP_NOTIFICATION_CHANNEL = "homingPigeon_channel_id";

    public static final String CONTACT_LIST = "ContactList";
}