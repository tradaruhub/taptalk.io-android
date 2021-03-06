package io.taptalk.TapTalk.Listener;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import io.taptalk.TapTalk.Interface.TapUIChatRoomInterface;
import io.taptalk.TapTalk.Manager.TAPChatManager;
import io.taptalk.TapTalk.Model.TAPMessageModel;
import io.taptalk.TapTalk.Model.TAPProductModel;
import io.taptalk.TapTalk.Model.TAPRoomModel;
import io.taptalk.TapTalk.Model.TAPUserModel;
import io.taptalk.TapTalk.View.Activity.TAPChatProfileActivity;
import io.taptalk.TapTalk.R;

import static io.taptalk.TapTalk.Const.TAPDefaultConstant.Extras.ROOM;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.K_USER;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.RequestCode.OPEN_GROUP_PROFILE;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.RequestCode.OPEN_MEMBER_PROFILE;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.RoomType.TYPE_GROUP;
import static io.taptalk.TapTalk.Const.TAPDefaultConstant.RoomType.TYPE_PERSONAL;

@Keep
public abstract class TapUIChatRoomListener implements TapUIChatRoomInterface {

    private String instanceKey = "";

    public TapUIChatRoomListener() {
    }

    public TapUIChatRoomListener(String instanceKey) {
        this.instanceKey = instanceKey;
    }

    @Override
    public void onTapTalkUserProfileButtonTapped(Activity activity, TAPRoomModel room, TAPUserModel user) {
        openTapTalkChatProfile(activity, room, null);
    }

    @Override
    public void onTapTalkGroupChatProfileButtonTapped(Activity activity, TAPRoomModel room) {
        openTapTalkChatProfile(activity, room, null);
    }

    @Override
    public void onTapTalkGroupMemberAvatarTapped(Activity activity, TAPRoomModel room, TAPUserModel user) {
        openTapTalkChatProfile(activity, room, user);
    }

    @Override
    public void onTapTalkUserMentionTapped(Activity activity, TAPMessageModel messageModel, TAPUserModel user, boolean isRoomParticipant) {
        if (isRoomParticipant) {
            // Open member profile
            TAPChatProfileActivity.start(activity, instanceKey, messageModel.getRoom(), user);
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
                    user);
        }
    }

    @Override
    public void onTapTalkMessageQuoteTapped(Activity activity, TAPMessageModel message, @Nullable HashMap<String, Object> userInfo) {

    }

    @Override
    public void onTapTalkProductListBubbleLeftOrSingleButtonTapped(Activity activity, TAPProductModel product, TAPRoomModel room, TAPUserModel recipient, boolean isSingleOption) {

    }

    @Override
    public void onTapTalkProductListBubbleRightButtonTapped(Activity activity, TAPProductModel product, TAPRoomModel room, TAPUserModel recipient, boolean isSingleOption) {

    }

    private void openTapTalkChatProfile(Activity activity, TAPRoomModel room, @Nullable TAPUserModel user) {
        if (null == activity) {
            return;
        }
        TAPChatProfileActivity.start(activity, instanceKey, room, user);
    }
}
