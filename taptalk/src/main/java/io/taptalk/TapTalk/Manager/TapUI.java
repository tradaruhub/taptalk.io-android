package io.taptalk.TapTalk.Manager;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.taptalk.TapTalk.API.View.TAPDefaultDataView;
import io.taptalk.TapTalk.Helper.TAPBaseCustomBubble;
import io.taptalk.TapTalk.Helper.TAPUtils;
import io.taptalk.TapTalk.Helper.TapTalk;
import io.taptalk.TapTalk.Listener.TAPDatabaseListener;
import io.taptalk.TapTalk.Listener.TapCommonListener;
import io.taptalk.TapTalk.Listener.TapUIChatRoomListener;
import io.taptalk.TapTalk.Listener.TapUICustomKeyboardListener;
import io.taptalk.TapTalk.Listener.TapUIRoomListListener;
import io.taptalk.TapTalk.Model.ResponseModel.TAPGetUserResponse;
import io.taptalk.TapTalk.Model.TAPCustomKeyboardItemModel;
import io.taptalk.TapTalk.Model.TAPErrorModel;
import io.taptalk.TapTalk.Model.TAPImageURL;
import io.taptalk.TapTalk.Model.TAPMessageModel;
import io.taptalk.TapTalk.Model.TAPProductModel;
import io.taptalk.TapTalk.Model.TAPRoomModel;
import io.taptalk.TapTalk.Model.TAPUserModel;
import io.taptalk.TapTalk.View.Activity.TAPAddGroupMemberActivity;
import io.taptalk.TapTalk.View.Activity.TAPBarcodeScannerActivity;
import io.taptalk.TapTalk.View.Activity.TAPChatProfileActivity;
import io.taptalk.TapTalk.View.Activity.TAPMyAccountActivity;
import io.taptalk.TapTalk.View.Activity.TAPNewChatActivity;
import io.taptalk.TapTalk.View.Activity.TapUIChatActivity;
import io.taptalk.TapTalk.View.Activity.TapUIRoomListActivity;
import io.taptalk.TapTalk.View.Fragment.TapUIMainRoomListFragment;

import static io.taptalk.TapTalk.Const.TAPDefaultConstant.ClientErrorCodes.ERROR_CODE_INIT_TAPTALK;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.ClientErrorCodes.ERROR_CODE_OTHERS;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.ClientErrorMessages.ERROR_MESSAGE_INIT_TAPTALK;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.ClientSuccessMessages.SUCCESS_MESSAGE_OPEN_ROOM;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.MessageData.USER_INFO;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.RoomType.TYPE_GROUP;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.RoomType.TYPE_PERSONAL;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.RoomType.TYPE_TRANSACTION;

public class TapUI {

    private static HashMap<String, TapUI> instances;

    private String instanceKey = "";

    private List<TapUIRoomListListener> tapUIRoomListListeners;
    private List<TapUIChatRoomListener> tapUIChatRoomListeners;
    private List<TapUICustomKeyboardListener> tapUICustomKeyboardListeners;

    private boolean isSearchChatBarHidden;
    private boolean isCloseRoomListButtonVisible;
    private boolean isMyAccountButtonHidden;
    private boolean isNewChatButtonHidden;
    private boolean isProfileButtonHidden;
    private boolean isNewContactMenuButtonHidden;
    private boolean isScanQRMenuButtonHidden;
    private boolean isNewGroupMenuButtonHidden;
    private boolean isLogoutButtonVisible;
    private boolean isReadStatusHidden;
    private boolean isConnectionStatusIndicatorHidden;
    private boolean isDocumentAttachmentDisabled;
    private boolean isCameraAttachmentDisabled;
    private boolean isGalleryAttachmentDisabled;
    private boolean isLocationAttachmentDisabled;
    private boolean isReplyMessageMenuDisabled;
    private boolean isForwardMessageMenuDisabled;
    private boolean isCopyMessageMenuDisabled;
    private boolean isDeleteMessageMenuDisabled;
    private boolean isSaveMediaToGalleryMenuDisabled;
    private boolean isSaveDocumentMenuDisabled;
    private boolean isOpenLinkMenuDisabled;
    private boolean isComposeEmailMenuDisabled;
    private boolean isDialNumberMenuDisabled;
    private boolean isSendSMSMenuDisabled;
    private boolean isViewProfileMenuDisabled;
    private boolean isSendMessageMenuDisabled;

    public TapUI(String instanceKey) {
        this.instanceKey = instanceKey;
    }

    public static TapUI getInstance() {
        return getInstance("");
    }

    public static TapUI getInstance(String instanceKey) {
        if (!getInstances().containsKey(instanceKey)) {
            TapUI instance = new TapUI(instanceKey);
            getInstances().put(instanceKey, instance);
        }
        return getInstances().get(instanceKey);
    }

    private static HashMap<String, TapUI> getInstances() {
        return null == instances ? instances = new HashMap<>() : instances;
    }

    private List<TapUIRoomListListener> getRoomListListeners() {
        return null == tapUIRoomListListeners ? tapUIRoomListListeners = new ArrayList<>() : tapUIRoomListListeners;
    }

    public void addRoomListListener(TapUIRoomListListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        getRoomListListeners().remove(listener);
        getRoomListListeners().add(listener);
    }

    public void removeRoomListListener(TapUIRoomListListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        getRoomListListeners().remove(listener);
    }

    private List<TapUIChatRoomListener> getChatRoomListeners() {
        return null == tapUIChatRoomListeners ? tapUIChatRoomListeners = new ArrayList<>() : tapUIChatRoomListeners;
    }

    public void addChatRoomListener(TapUIChatRoomListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        getChatRoomListeners().remove(listener);
        getChatRoomListeners().add(listener);
    }

    public void removeChatRoomListener(TapUIChatRoomListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        getChatRoomListeners().remove(listener);
    }

    private List<TapUICustomKeyboardListener> getCustomKeyboardListeners() {
        return null == tapUICustomKeyboardListeners ? tapUICustomKeyboardListeners = new ArrayList<>() : tapUICustomKeyboardListeners;
    }

    public void addCustomKeyboardListener(TapUICustomKeyboardListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        getCustomKeyboardListeners().remove(listener);
        getCustomKeyboardListeners().add(listener);
    }

    public void removeCustomKeyboardListener(TapUICustomKeyboardListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        getCustomKeyboardListeners().remove(listener);
    }

    public TapUIMainRoomListFragment getRoomListFragment() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return null;
        }
        return TapUIMainRoomListFragment.newInstance(instanceKey);
    }

    public void openRoomList(Context context) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TapUIRoomListActivity.start(context, instanceKey);
    }

    public void openGroupChatCreator(Context context) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TAPAddGroupMemberActivity.start(context, instanceKey);
    }

    public void openNewChat(Context context) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TAPNewChatActivity.start(context, instanceKey);
    }

    public void openBarcodeScanner(Context context) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TAPBarcodeScannerActivity.start(context, instanceKey);
    }

    public void openChatRoomWithRoomModel(Context context, TAPRoomModel roomModel) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TapUIChatActivity.start(context, instanceKey, roomModel, null, null, TapTalk.isConnected(instanceKey));
    }

    public void openChatRoomWithRoomModel(Context context, TAPRoomModel roomModel, String scrollToMessageWithLocalID) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TapUIChatActivity.start(context, instanceKey, roomModel, null, scrollToMessageWithLocalID, TapTalk.isConnected(instanceKey));
    }

    public void openChatRoom(Context context, String roomID, String roomName, TAPImageURL roomImage, int roomType, String roomColor) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TapUIChatActivity.start(
                context,
                instanceKey,
                roomID,
                roomName,
                roomImage,
                roomType,
                roomColor);
    }

    public void openChatRoomWithUserID(
            Context context,
            String userID,
            @Nullable String prefilledText,
            @Nullable String customQuoteTitle,
            @Nullable String customQuoteContent,
            @Nullable String customQuoteImageURL,
            @Nullable HashMap<String, Object> userInfo,
            TapCommonListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            listener.onError(ERROR_CODE_INIT_TAPTALK, ERROR_MESSAGE_INIT_TAPTALK);
            return;
        }
        String roomID = TAPChatManager.getInstance(instanceKey).arrangeRoomId(
                TAPChatManager.getInstance(instanceKey).getActiveUser().getUserID(),
                userID);
        if (null != customQuoteTitle) {
            TAPChatManager.getInstance(instanceKey).setQuotedMessage(roomID, customQuoteTitle, customQuoteContent, customQuoteImageURL);
            if (null != userInfo) {
                TAPChatManager.getInstance(instanceKey).saveUserInfo(roomID, userInfo);
            }
        }
        if (null != prefilledText) {
            TAPChatManager.getInstance(instanceKey).saveMessageToDraft(roomID, prefilledText);
        }
        TAPUserModel user = TAPContactManager.getInstance(instanceKey).getUserData(userID);

        if (null == user) {
            TAPDataManager.getInstance(instanceKey).getUserByIdFromApi(userID, new TAPDefaultDataView<TAPGetUserResponse>() {
                @Override
                public void onSuccess(TAPGetUserResponse response) {
                    openChatRoom(
                            context,
                            roomID,
                            response.getUser().getName(),
                            response.getUser().getAvatarURL(),
                            TYPE_PERSONAL,
                            "");
                    if (null != listener) {
                        listener.onSuccess(SUCCESS_MESSAGE_OPEN_ROOM);
                    }
                }

                @Override
                public void onError(TAPErrorModel error) {
                    if (null != listener) {
                        listener.onError(error.getCode(), error.getMessage());
                    }
                }

                @Override
                public void onError(String errorMessage) {
                    if (null != listener) {
                        listener.onError(ERROR_CODE_OTHERS, errorMessage);
                    }
                }
            });
        } else {
            openChatRoom(
                    context,
                    roomID,
                    user.getName(),
                    user.getAvatarURL(),
                    TYPE_PERSONAL,
                    "");
            if (null != listener) {
                listener.onSuccess(SUCCESS_MESSAGE_OPEN_ROOM);
            }
        }
    }

    public void openChatRoomWithXCUserID(
            Context context,
            String xcUserID,
            @Nullable String prefilledText,
            @Nullable String customQuoteTitle,
            @Nullable String customQuoteContent,
            @Nullable String customQuoteImageURL,
            @Nullable HashMap<String, Object> userInfo,
            TapCommonListener listener) {
        if (!TapTalk.checkTapTalkInitialized()) {
            listener.onError(ERROR_CODE_INIT_TAPTALK, ERROR_MESSAGE_INIT_TAPTALK);
            return;
        }
        TAPUtils.getUserFromXcUserID(instanceKey, xcUserID, new TAPDatabaseListener<TAPUserModel>() {
            @Override
            public void onSelectFinished(TAPUserModel user) {
                String roomID = TAPChatManager.getInstance(instanceKey).arrangeRoomId(
                        TAPChatManager.getInstance(instanceKey).getActiveUser().getUserID(),
                        user.getUserID());
                if (null != customQuoteTitle) {
                    TAPChatManager.getInstance(instanceKey).setQuotedMessage(roomID, customQuoteTitle, customQuoteContent, customQuoteImageURL);
                    if (null != userInfo) {
                        TAPChatManager.getInstance(instanceKey).saveUserInfo(roomID, userInfo);
                    }
                }
                if (null != prefilledText) {
                    TAPChatManager.getInstance(instanceKey).saveMessageToDraft(roomID, prefilledText);
                }
                openChatRoom(
                        context,
                        roomID,
                        user.getName(),
                        user.getAvatarURL(),
                        TYPE_PERSONAL,
                        "");
                if (null != listener) {
                    listener.onSuccess(SUCCESS_MESSAGE_OPEN_ROOM);
                }
            }

            @Override
            public void onSelectFailed(String errorMessage) {
                if (null != listener) {
                    listener.onError(ERROR_CODE_OTHERS, errorMessage);
                }
            }
        });
    }

    public void openChatRoomWithOtherUser(Context context, TAPUserModel otherUser) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        openChatRoom(
                context,
                TAPChatManager.getInstance(instanceKey).arrangeRoomId(TAPChatManager.getInstance(instanceKey).getActiveUser().getUserID(), otherUser.getUserID()),
                otherUser.getName(),
                otherUser.getAvatarURL(),
                TYPE_PERSONAL,
                "");
    }

    public void openChatRoomWithRoomModel(
            Context context,
            TAPRoomModel roomModel,
            @Nullable String prefilledText,
            @Nullable String customQuoteTitle,
            @Nullable String customQuoteContent,
            @Nullable String customQuoteImageURL,
            @Nullable HashMap<String, Object> userInfo) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        if (null != customQuoteTitle) {
            TAPChatManager.getInstance(instanceKey).setQuotedMessage(roomModel.getRoomID(), customQuoteTitle, customQuoteContent, customQuoteImageURL);
            if (null != userInfo) {
                TAPChatManager.getInstance(instanceKey).saveUserInfo(roomModel.getRoomID(), userInfo);
            }
        }
        openChatRoomWithRoomModel(context, roomModel);
    }

    public boolean isSearchChatBarVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return !isSearchChatBarHidden;
    }

    public void setSearchChatBarInRoomListVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isSearchChatBarHidden = !isVisible;
    }

    public boolean isCloseRoomListButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return isCloseRoomListButtonVisible;
    }

    public void setCloseButtonInRoomListVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isCloseRoomListButtonVisible = isVisible;
    }

    public boolean isMyAccountButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return !isMyAccountButtonHidden;
    }

    public void setMyAccountButtonInRoomListVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isMyAccountButtonHidden = !isVisible;
    }

    public boolean isNewChatButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return !isNewChatButtonHidden;
    }

    public void setNewChatButtonInRoomListVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isNewChatButtonHidden = !isVisible;
    }

    public boolean isProfileButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return !isProfileButtonHidden;
    }

    public void setProfileButtonInChatRoomVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isProfileButtonHidden = !isVisible;
    }

    public boolean isNewContactMenuButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return !isNewContactMenuButtonHidden;
    }

    public void setNewContactMenuButtonVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isNewContactMenuButtonHidden = !isVisible;
    }

    public boolean isScanQRMenuButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return !isScanQRMenuButtonHidden;
    }

    public void setScanQRMenuButtonVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isScanQRMenuButtonHidden = !isVisible;
    }

    public boolean isNewGroupMenuButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return !isNewGroupMenuButtonHidden;
    }

    public void setNewGroupMenuButtonVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isNewGroupMenuButtonHidden = !isVisible;
    }

    public boolean isLogoutButtonVisible() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return isLogoutButtonVisible;
    }

    public void setLogoutButtonVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isLogoutButtonVisible = isVisible;
    }

    public boolean isReadStatusHidden() {
        if (!TapTalk.checkTapTalkInitialized()) {
            return false;
        }
        return isReadStatusHidden;
    }

    public void setReadStatusHidden(boolean isReadStatusHidden) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        this.isReadStatusHidden = isReadStatusHidden;
    }

    public boolean isConnectionStatusIndicatorVisible() {
        return !isConnectionStatusIndicatorHidden;
    }

    public void setConnectionStatusIndicatorVisible(boolean isVisible) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        isConnectionStatusIndicatorHidden = !isVisible;
    }

    public boolean isDocumentAttachmentDisabled() {
        return isDocumentAttachmentDisabled;
    }

    public void setDocumentAttachmentDisabled(boolean documentAttachmentDisabled) {
        isDocumentAttachmentDisabled = documentAttachmentDisabled;
    }

    public boolean isCameraAttachmentDisabled() {
        return isCameraAttachmentDisabled;
    }

    public void setCameraAttachmentDisabled(boolean cameraAttachmentDisabled) {
        isCameraAttachmentDisabled = cameraAttachmentDisabled;
    }

    public boolean isGalleryAttachmentDisabled() {
        return isGalleryAttachmentDisabled;
    }

    public void setGalleryAttachmentDisabled(boolean galleryAttachmentDisabled) {
        isGalleryAttachmentDisabled = galleryAttachmentDisabled;
    }

    public boolean isLocationAttachmentDisabled() {
        return isLocationAttachmentDisabled;
    }

    public void setLocationAttachmentDisabled(boolean locationAttachmentDisabled) {
        isLocationAttachmentDisabled = locationAttachmentDisabled;
    }

    public boolean isReplyMessageMenuDisabled() {
        return isReplyMessageMenuDisabled;
    }

    public void setReplyMessageMenuDisabled(boolean replyMessageMenuDisabled) {
        isReplyMessageMenuDisabled = replyMessageMenuDisabled;
    }

    public boolean isForwardMessageMenuDisabled() {
        return isForwardMessageMenuDisabled;
    }

    public void setForwardMessageMenuDisabled(boolean forwardMessageMenuDisabled) {
        isForwardMessageMenuDisabled = forwardMessageMenuDisabled;
    }

    public boolean isCopyMessageMenuDisabled() {
        return isCopyMessageMenuDisabled;
    }

    public void setCopyMessageMenuDisabled(boolean copyMessageMenuDisabled) {
        isCopyMessageMenuDisabled = copyMessageMenuDisabled;
    }

    public boolean isDeleteMessageMenuDisabled() {
        return isDeleteMessageMenuDisabled;
    }

    public void setDeleteMessageMenuDisabled(boolean deleteMessageMenuDisabled) {
        isDeleteMessageMenuDisabled = deleteMessageMenuDisabled;
    }

    public boolean isSaveMediaToGalleryMenuDisabled() {
        return isSaveMediaToGalleryMenuDisabled;
    }

    public void setSaveMediaToGalleryMenuDisabled(boolean saveMediaToGalleryMenuDisabled) {
        isSaveMediaToGalleryMenuDisabled = saveMediaToGalleryMenuDisabled;
    }

    public boolean isSaveDocumentMenuDisabled() {
        return isSaveDocumentMenuDisabled;
    }

    public void setSaveDocumentMenuDisabled(boolean saveDocumentMenuDisabled) {
        isSaveDocumentMenuDisabled = saveDocumentMenuDisabled;
    }

    public boolean isOpenLinkMenuDisabled() {
        return isOpenLinkMenuDisabled;
    }

    public void setOpenLinkMenuDisabled(boolean openLinkMenuDisabled) {
        isOpenLinkMenuDisabled = openLinkMenuDisabled;
    }

    public boolean isComposeEmailMenuDisabled() {
        return isComposeEmailMenuDisabled;
    }

    public void setComposeEmailMenuDisabled(boolean composeEmailMenuDisabled) {
        isComposeEmailMenuDisabled = composeEmailMenuDisabled;
    }

    public boolean isDialNumberMenuDisabled() {
        return isDialNumberMenuDisabled;
    }

    public void setDialNumberMenuDisabled(boolean dialNumberMenuDisabled) {
        isDialNumberMenuDisabled = dialNumberMenuDisabled;
    }

    public boolean isSendSMSMenuDisabled() {
        return isSendSMSMenuDisabled;
    }

    public void setSendSMSMenuDisabled(boolean sendSMSMenuDisabled) {
        isSendSMSMenuDisabled = sendSMSMenuDisabled;
    }

    public boolean isViewProfileMenuDisabled() {
        return isViewProfileMenuDisabled;
    }

    public void setViewProfileMenuDisabled(boolean viewProfileMenuDisabled) {
        isViewProfileMenuDisabled = viewProfileMenuDisabled;
    }

    public boolean isSendMessageMenuDisabled() {
        return isSendMessageMenuDisabled;
    }

    public void setSendMessageMenuDisabled(boolean sendMessageMenuDisabled) {
        isSendMessageMenuDisabled = sendMessageMenuDisabled;
    }

    public void addCustomBubble(TAPBaseCustomBubble baseCustomBubble) {
        if (!TapTalk.checkTapTalkInitialized()) {
            return;
        }
        TAPCustomBubbleManager.getInstance(instanceKey).addCustomBubbleMap(baseCustomBubble);
    }

    void triggerSearchChatBarTapped(Activity activity, TapUIMainRoomListFragment mainRoomListFragment) {
        if (getRoomListListeners().isEmpty() && null != mainRoomListFragment) {
            mainRoomListFragment.showSearchChat();
        } else {
            for (TapUIRoomListListener listener : getRoomListListeners()) {
                if (null != listener) {
                    listener.onSearchChatBarTapped(activity, mainRoomListFragment);
                }
            }
        }
    }

    void triggerCloseRoomListTapped(Activity activity) {
        if (getRoomListListeners().isEmpty() && null != activity) {
            activity.onBackPressed();
        } else {
            for (TapUIRoomListListener listener : getRoomListListeners()) {
                if (null != listener) {
                    listener.onCloseRoomListTapped(activity);
                }
            }
        }
    }

    void triggerTapTalkAccountButtonTapped(Activity activity) {
        if (getRoomListListeners().isEmpty()) {
            TAPMyAccountActivity.Companion.start(activity, instanceKey);
        } else {
            for (TapUIRoomListListener listener : getRoomListListeners()) {
                if (null != listener) {
                    listener.onTapTalkAccountButtonTapped(activity);
                }
            }
        }
    }

    void triggerNewChatButtonTapped(Activity activity) {
        if (getRoomListListeners().isEmpty()) {
            TAPNewChatActivity.start(activity, instanceKey);
        } else {
            for (TapUIRoomListListener listener : getRoomListListeners()) {
                if (null != listener) {
                    listener.onNewChatButtonTapped(activity);
                }
            }
        }
    }

    void triggerChatRoomProfileButtonTapped(Activity activity, TAPRoomModel room, @Nullable TAPUserModel user) {
        if (room.getRoomType() == TYPE_TRANSACTION) {
            return;
        }
        if (getChatRoomListeners().isEmpty()) {
            TAPChatProfileActivity.start(activity, instanceKey, room, user);
        } else {
            for (TapUIChatRoomListener listener : getChatRoomListeners()) {
                if (room.getRoomType() == TYPE_PERSONAL) {
                    if (null != listener) {
                        listener.onTapTalkUserProfileButtonTapped(activity, room, user);
                    }
                } else if (room.getRoomType() == TYPE_GROUP && null != user) {
                    if (null != listener) {
                        listener.onTapTalkGroupMemberAvatarTapped(activity, room, user);
                    }
                } else if (room.getRoomType() == TYPE_GROUP) {
                    if (null != listener) {
                        listener.onTapTalkGroupChatProfileButtonTapped(activity, room);
                    }
                }
            }
        }
    }

    void triggerUserMentionTapped(Activity activity, TAPMessageModel message, TAPUserModel user, boolean isRoomParticipant) {
        if (getChatRoomListeners().isEmpty()) {
            if (isRoomParticipant) {
                // Open member profile
                TAPChatProfileActivity.start(activity, instanceKey, message.getRoom(), user);
            } else {
                // Open personal profile
                TAPChatProfileActivity.start(
                        activity,
                        instanceKey,
                        TAPRoomModel.Builder(
                                TAPChatManager.getInstance(instanceKey).arrangeRoomId(
                                        TAPChatManager.getInstance(instanceKey).getActiveUser().getUserID(),
                                        user.getUserID()),
                                user.getName(),
                                TYPE_PERSONAL,
                                user.getAvatarURL(),
                                ""), // TODO: 13 Apr 2020 ROOM COLOR
                        user,
                        true);
            }
        } else {
            for (TapUIChatRoomListener listener : getChatRoomListeners()) {
                listener.onTapTalkUserMentionTapped(activity, message, user, isRoomParticipant);
            }
        }
    }

    void triggerMessageQuoteTapped(Activity activity, TAPMessageModel messageModel) {
        for (TapUIChatRoomListener listener : getChatRoomListeners()) {
            HashMap<String, Object> userInfo = null;
            if (null != messageModel.getData() && null != messageModel.getData().get(USER_INFO)) {
                userInfo = (HashMap<String, Object>) messageModel.getData().get(USER_INFO);
            }
            if (null != listener) {
                listener.onTapTalkMessageQuoteTapped(activity, messageModel, userInfo);
            }
        }
    }

    void triggerProductListBubbleLeftOrSingleButtonTapped(Activity activity, TAPProductModel product, TAPRoomModel room, TAPUserModel recipient, boolean isSingleOption) {
        for (TapUIChatRoomListener listener : getChatRoomListeners()) {
            if (null != listener) {
                listener.onTapTalkProductListBubbleLeftOrSingleButtonTapped(activity, product, room, recipient, isSingleOption);
            }
        }
    }

    void triggerProductListBubbleRightButtonTapped(Activity activity, TAPProductModel product, TAPRoomModel room, TAPUserModel recipient, boolean isSingleOption) {
        for (TapUIChatRoomListener listener : getChatRoomListeners()) {
            if (null != listener) {
                listener.onTapTalkProductListBubbleRightButtonTapped(activity, product, room, recipient, isSingleOption);
            }
        }
    }

    List<TAPCustomKeyboardItemModel> getCustomKeyboardItems(TAPRoomModel room, TAPUserModel activeUser, TAPUserModel recipientUser) {
        for (TapUICustomKeyboardListener listener : getCustomKeyboardListeners()) {
            List<TAPCustomKeyboardItemModel> items = listener.setCustomKeyboardItems(room, activeUser, recipientUser);
            if (null != items && !items.isEmpty()) {
                return items;
            }
        }
        return null;
    }

    void triggerCustomKeyboardItemTapped(Activity activity, TAPCustomKeyboardItemModel customKeyboardItemModel, TAPRoomModel room, TAPUserModel activeUser, TAPUserModel recipientUser) {
        for (TapUICustomKeyboardListener listener : getCustomKeyboardListeners()) {
            if (null != listener) {
                listener.onCustomKeyboardItemTapped(activity, customKeyboardItemModel, room, activeUser, recipientUser);
            }
        }
    }
}
