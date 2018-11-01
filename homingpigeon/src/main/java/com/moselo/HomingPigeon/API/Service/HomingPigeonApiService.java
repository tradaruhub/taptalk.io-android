package com.moselo.HomingPigeon.API.Service;

import com.moselo.HomingPigeon.Model.RequestModel.HpGetUserByIdRequest;
import com.moselo.HomingPigeon.Model.RequestModel.HpGetUserByUsernameRequest;
import com.moselo.HomingPigeon.Model.RequestModel.HpGetUserByXcUserIdRequest;
import com.moselo.HomingPigeon.Model.RequestModel.HpPushNotificationRequest;
import com.moselo.HomingPigeon.Model.RequestModel.HpUserIdRequest;
import com.moselo.HomingPigeon.Model.ResponseModel.BaseResponse;
import com.moselo.HomingPigeon.Model.RequestModel.HpCommonRequest;
import com.moselo.HomingPigeon.Model.RequestModel.HpGetMessageListbyRoomAfterRequest;
import com.moselo.HomingPigeon.Model.RequestModel.HpGetMessageListbyRoomBeforeRequest;
import com.moselo.HomingPigeon.Model.ResponseModel.HpAuthTicketResponse;
import com.moselo.HomingPigeon.Model.ResponseModel.HpCommonResponse;
import com.moselo.HomingPigeon.Model.ResponseModel.HpContactResponse;
import com.moselo.HomingPigeon.Model.ResponseModel.HpGetAccessTokenResponse;
import com.moselo.HomingPigeon.Model.RequestModel.HpAuthTicketRequest;
import com.moselo.HomingPigeon.Model.ResponseModel.HpGetMessageListbyRoomResponse;
import com.moselo.HomingPigeon.Model.ResponseModel.HpGetRoomListResponse;
import com.moselo.HomingPigeon.Model.ResponseModel.HpGetUserResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface HomingPigeonApiService {
    String BASE_URL = "https://hp-staging.moselo.com:8080/api/v1/";

    @POST("server/auth_ticket/request")
    Observable<BaseResponse<HpAuthTicketResponse>> getAuthTicket(@Body HpAuthTicketRequest request);

    @POST("auth/access_token/request")
    Observable<BaseResponse<HpGetAccessTokenResponse>> getAccessToken();

    @POST("chat/message/room_list_and_unread")
    Observable<BaseResponse<HpGetRoomListResponse>> getRoomList(@Body HpCommonRequest request);

    @POST("chat/message/new_and_updated")
    Observable<BaseResponse<HpGetRoomListResponse>> getPendingAndUpdatedMessage();

    @POST("chat/message/list_by_room/before")
    Observable<BaseResponse<HpGetMessageListbyRoomResponse>> getMessageListByRoomBefore(@Body HpGetMessageListbyRoomBeforeRequest request);

    @POST("client/contact/list")
    Observable<BaseResponse<HpContactResponse>> getMyContactListFromAPI();

    //HpCommonResponse itu temporary nnti kalau udah ada response yang bener harus di ganti
    @POST("client/push_notification/update")
    Observable<BaseResponse<HpCommonResponse>> registerFcmTokenToServer(@Body HpPushNotificationRequest request);

    @POST("chat/message/list_by_room/after")
    Observable<BaseResponse<HpGetMessageListbyRoomResponse>> getMessageListByRoomAfter(@Body HpGetMessageListbyRoomAfterRequest request);

    @POST("client/contact/add")
    Observable<BaseResponse<HpCommonResponse>> addContact(@Body HpUserIdRequest request);

    @POST("client/contact/remove")
    Observable<BaseResponse<HpCommonResponse>> removeContact(@Body HpUserIdRequest request);

    @POST("client/user/get_by_id")
    Observable<BaseResponse<HpGetUserResponse>> getUserByID(@Body HpGetUserByIdRequest request);

    @POST("client/user/get_by_xcuserid")
    Observable<BaseResponse<HpGetUserResponse>> getUserByXcUserID(@Body HpGetUserByXcUserIdRequest request);

    @POST("client/user/get_by_username")
    Observable<BaseResponse<HpGetUserResponse>> getUserByUsername(@Body HpGetUserByUsernameRequest request);
}
