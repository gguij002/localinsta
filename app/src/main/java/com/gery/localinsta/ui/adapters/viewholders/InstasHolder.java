package com.gery.localinsta.ui.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gery.localinsta.LiApplication;
import com.gery.localinsta.R;
import com.gery.localinsta.model.rest.response.Datum;
import com.gery.localinsta.ui.adapters.listener.ListItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

import static com.gery.localinsta.ui.adapters.InstasAdapter.HEADER_VIEW;

public class InstasHolder extends RecyclerView.ViewHolder {

    private final boolean gridView;
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


    public InstasHolder(View itemView, boolean gridView) {
        super(itemView);
        this.gridView = gridView;
        ButterKnife.bind(this, itemView);
    }

    public static View inflateView(ViewGroup parent, int viewType, boolean gridView) {
        View view;
        if (viewType == HEADER_VIEW) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_header, parent, false);
        } else if (gridView) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item_grid_view, parent, false);
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_item_regular_view, parent, false);
            View instaImage = view.findViewById(R.id.insta_image);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
            params.setMargins(5, 50, 10, 0); //left, top, right, bottom
            instaImage.setLayoutParams(params);

        }
        return view;
    }

    public void onBind(Datum message, ListItemClickListener<Datum> listener) {
        this.data = message;


        if (getItemViewType() == HEADER_VIEW) {
            locationName.setText(LiApplication.getContext().getString(R.string.posts_near_you));
            return;
        }

        Glide.with(LiApplication.getContext())
                .load(data.getImages().getStandardResolution().getUrl())
                .into(image);

        if (gridView) {
            return;
        }

        userName.setText(data.getUser().getUsername());
        caption.setText(data.getCaption().getText());

        itemView.setOnClickListener(view -> {
            listener.onItemClicked(data);
        });

        Glide.with(LiApplication.getContext())
                .load(data.getUser().getProfilePicture())
                .into(userPhoto);
    }
}
