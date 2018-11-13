package com.moselo.HomingPigeon.View.Adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moselo.HomingPigeon.Helper.TAPBaseViewHolder;
import com.moselo.HomingPigeon.Listener.TAPAttachmentListener;
import com.moselo.HomingPigeon.Model.TAPAttachmentModel;
import com.moselo.HomingPigeon.R;

import static com.moselo.HomingPigeon.Model.TAPAttachmentModel.ID_AUDIO;
import static com.moselo.HomingPigeon.Model.TAPAttachmentModel.ID_CAMERA;
import static com.moselo.HomingPigeon.Model.TAPAttachmentModel.ID_CONTACT;
import static com.moselo.HomingPigeon.Model.TAPAttachmentModel.ID_DOCUMENT;
import static com.moselo.HomingPigeon.Model.TAPAttachmentModel.ID_GALLERY;
import static com.moselo.HomingPigeon.Model.TAPAttachmentModel.ID_LOCATION;
import static com.moselo.HomingPigeon.Model.TAPAttachmentModel.createAttachMenu;

public class TAPAttachmentAdapter extends TAPBaseAdapter<TAPAttachmentModel, TAPBaseViewHolder<TAPAttachmentModel>> {

    private TAPAttachmentListener attachmentListener;
    View.OnClickListener onClickListener;

    public TAPAttachmentAdapter(TAPAttachmentListener attachmentListener, View.OnClickListener onClickListener) {
        this.attachmentListener = attachmentListener;
        this.onClickListener = onClickListener;
        setItems(createAttachMenu(), false);
    }

    @NonNull
    @Override
    public TAPBaseViewHolder<TAPAttachmentModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AttachmentVH(parent, R.layout.tap_cell_attachment_menu);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public class AttachmentVH extends TAPBaseViewHolder<TAPAttachmentModel> {

        private ImageView ivAttachIcon;
        private TextView tvAttachTitle;
        private View vAttachMenuSeparator;

        AttachmentVH(ViewGroup parent, int itemLayoutId) {
            super(parent, itemLayoutId);
            ivAttachIcon = itemView.findViewById(R.id.iv_attach_icon);
            tvAttachTitle = itemView.findViewById(R.id.tv_attach_title);
            vAttachMenuSeparator = itemView.findViewById(R.id.v_attach_menu_separator);
        }

        @Override
        protected void onBind(TAPAttachmentModel item, int position) {
            ivAttachIcon.setImageDrawable(itemView.getResources().getDrawable(item.getIcon()));
            tvAttachTitle.setText(itemView.getResources().getText(item.getTitleIds()));

            if (getItemCount() - 1 == position)
                vAttachMenuSeparator.setVisibility(View.GONE);
            else vAttachMenuSeparator.setVisibility(View.VISIBLE);

            itemView.setOnClickListener(v -> onAttachmentClicked(item));
        }

        private void onAttachmentClicked(TAPAttachmentModel item) {
            switch (item.getId()) {
                case ID_DOCUMENT:
                    attachmentListener.onDocumentSelected();
                    break;
                case ID_CAMERA:
                    attachmentListener.onCameraSelected();
                    break;
                case ID_GALLERY:
                    attachmentListener.onGallerySelected();
                    break;
                case ID_AUDIO:
                    attachmentListener.onAudioSelected();
                    break;
                case ID_LOCATION:
                    attachmentListener.onLocationSelected();
                    break;
                case ID_CONTACT:
                    attachmentListener.onContactSelected();
                    break;
            }
            onClickListener.onClick(itemView);
        }
    }
}
