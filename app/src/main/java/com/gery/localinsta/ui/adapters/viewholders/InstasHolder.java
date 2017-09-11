package com.gery.localinsta.ui.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gery.localinsta.LiApplication;
import com.gery.localinsta.R;
import com.gery.localinsta.model.rest.response.Datum;
import com.gery.localinsta.ui.adapters.listener.ListItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import io.reactivex.annotations.Nullable;

import static com.gery.localinsta.ui.adapters.InstasAdapter.HEADER_VIEW;

/**
 * Created by peterbekos on 5/16/17.
 */

public class InstasHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.insta_image)
    ImageView image;
    @Nullable
    @BindView(R.id.user_photo)
    ImageView userPhoto;
    @Nullable
    @BindView(R.id.caption)
    TextView caption;
    @Nullable
    @BindView(R.id.user_name)
    TextView userName;
    @Nullable
    @BindView(R.id.location_name)
    TextView locationName;

    private Datum data;


    public InstasHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static View inflateView(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return inflater.inflate(R.layout.list_header, parent, false);
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return inflater.inflate(R.layout.insta_large_view, parent, false);
        }
    }

    public void onBind(Datum message, ListItemClickListener<Datum> listener) {
        this.data = message;

        if (getItemViewType() == HEADER_VIEW) {
            locationName.setText(LiApplication.getContext().getString(R.string.posts_near_you));
            return;
        }

        userName.setText(data.getUser().getUsername());
        caption.setText(data.getCaption().getText());

        itemView.setOnClickListener(view -> {
            listener.onItemClicked(data);
        });

        Glide.with(LiApplication.getContext())
                .load(data.getImages().getStandardResolution().getUrl())
                .into(image);

        Glide.with(LiApplication.getContext())
                .load(data.getUser().getProfilePicture())
                .into(userPhoto);
    }
}
