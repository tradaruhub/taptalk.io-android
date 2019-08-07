package io.taptalk.TapTalk.Helper;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import io.taptalk.TapTalk.API.View.TapSendMessageInterface;
import io.taptalk.TapTalk.Const.TAPDefaultConstant;
import io.taptalk.TapTalk.Manager.TAPChatManager;
import io.taptalk.TapTalk.Model.TAPMediaPreviewModel;
import io.taptalk.TapTalk.Model.TAPMessageModel;
import io.taptalk.TapTalk.Model.TAPRoomModel;

public class TapCoreMessageManager {

    private HashMap<String, TapSendMessageInterface> listeners;

    public static void markAsRead(TAPMessageModel message) {

    }

    public static void sendTextMessage(String message, TAPRoomModel room, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().sendTextMessageWithRoomModel(message, room, listener);
    }

    public static void sendLocationMessage(Double latitude, Double longitude, String address, TAPRoomModel room, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().sendLocationMessage(address, latitude, longitude, room, listener);
    }

    public static void sendImageMessage(Bitmap image, String caption, TAPRoomModel room, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().sendImageMessage(TapTalk.appContext, room, image, caption, listener);
    }

    public static void sendImageMessage(Uri uri, String caption, TAPRoomModel room, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().sendImageMessage(TapTalk.appContext, room, uri, caption, listener);
    }

    public static void sendVideoMessage(Uri uri, String caption, TAPRoomModel room, TapSendMessageInterface listener) {
        ArrayList<TAPMediaPreviewModel> videos = new ArrayList<>();
        TAPMediaPreviewModel model = new TAPMediaPreviewModel(uri, TAPDefaultConstant.MessageType.TYPE_VIDEO, false);
        videos.add(model);
        TAPChatManager.getInstance().sendImageOrVideoMessage(TapTalk.appContext, room, videos, listener);
    }

    public static void sendFileMessage(File file, TAPRoomModel room, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().sendFileMessage(TapTalk.appContext, room, file, listener);
    }


    public static void sendTextMessage(String message, TAPRoomModel room, TAPMessageModel quote, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().setQuotedMessage(room.getRoomID(), quote, TAPDefaultConstant.QuoteAction.REPLY);
        TAPChatManager.getInstance().sendTextMessageWithRoomModel(message, room, listener);
    }

    public static void sendLocationMessage(Double latitude, Double longitude, String address, TAPRoomModel room, TAPMessageModel quote, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().setQuotedMessage(room.getRoomID(), quote, TAPDefaultConstant.QuoteAction.REPLY);
        TAPChatManager.getInstance().sendLocationMessage(address, latitude, longitude, room, listener);
    }

    public static void sendImageMessage(Bitmap image, String caption, TAPRoomModel room, TAPMessageModel quote, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().setQuotedMessage(room.getRoomID(), quote, TAPDefaultConstant.QuoteAction.REPLY);
        TAPChatManager.getInstance().sendImageMessage(TapTalk.appContext, room, image, caption, listener);
    }

    public static void sendImageMessage(Uri uri, String caption, TAPRoomModel room, TAPMessageModel quote, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().setQuotedMessage(room.getRoomID(), quote, TAPDefaultConstant.QuoteAction.REPLY);
        TAPChatManager.getInstance().sendImageMessage(TapTalk.appContext, room, uri, caption, listener);
    }

    public static void sendVideoMessage(Uri uri, String caption, TAPRoomModel room, TAPMessageModel quote, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().setQuotedMessage(room.getRoomID(), quote, TAPDefaultConstant.QuoteAction.REPLY);
        ArrayList<TAPMediaPreviewModel> videos = new ArrayList<>();
        TAPMediaPreviewModel model = new TAPMediaPreviewModel(uri, TAPDefaultConstant.MessageType.TYPE_VIDEO, false);
        videos.add(model);
        TAPChatManager.getInstance().sendImageOrVideoMessage(TapTalk.appContext, room, videos, listener);
    }

    public static void sendFileMessage(File file, TAPRoomModel room, TAPMessageModel quote, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().setQuotedMessage(room.getRoomID(), quote, TAPDefaultConstant.QuoteAction.REPLY);
        TAPChatManager.getInstance().sendFileMessage(TapTalk.appContext, room, file, listener);
    }

    // TODO: 2019-08-06 Resend
    public static void resend(TAPMessageModel tapMessageModel, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().resendMessage(tapMessageModel, listener);
    }

    // TODO: 2019-08-06 Forward
    public static void forward(TAPMessageModel quote, TAPRoomModel room, TapSendMessageInterface listener) {
        TAPChatManager.getInstance().setQuotedMessage(room.getRoomID(), quote, TAPDefaultConstant.QuoteAction.FORWARD);
        TAPChatManager.getInstance().checkAndSendForwardedMessage(room, listener);
    }
}
