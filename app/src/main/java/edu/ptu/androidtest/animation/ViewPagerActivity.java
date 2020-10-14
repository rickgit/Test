package edu.ptu.androidtest.animation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import edu.ptu.androidtest.R;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;
import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT;

public class ViewPagerActivity extends FragmentActivity {
    public static class VF extends Fragment {
        public static VF createFragment(int tag){
            VF vf = new VF();
            Bundle args = new Bundle();
            args.putInt("tag",tag);
            vf.setArguments(args);
            return vf;
        }


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            FrameLayout button = new FrameLayout(container.getContext());
//            button.setId(R.id.fl_container);
//            button.setTag(getArguments().getInt("tag"));
            View inflate = inflater.inflate(R.layout.framlayout, container, false);
            int tag = getArguments().getInt("tag");
            inflate.setTag(tag);
            if (tag %2==0)
                inflate.setBackgroundColor(0xff00ff00);
            else
                inflate.setBackgroundColor(0xff00ffff);

            return inflate;
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPager2 viewPager2 = new ViewPager2(this);
        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return null;
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        setContentView(R.layout.vp);
        ViewPager view=findViewById(R.id.vp);
        view.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return VF.createFragment(position);
            }

            @Override
            public int getCount() {
                return 10;
            }
        });
        view.setPageTransformer(true, new ViewPager.PageTransformer() {
            private static final float MIN_SCALE = 0.85f;
            private static final float MIN_ALPHA = 0.5f;
            @Override
            public void transformPage(@NonNull View page, float position) {

                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0f);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0f);
                }
//                if (position<-1){
//                    System.out.println("<<<<<<<<<<<<<<<<<  setPageTransformer "+page.getTag()+": "+position);
//                }else if (position<=1){
//                    System.out.println("================  setPageTransformer "+page.getTag()+": "+position);
//
//                }else if (position>1){
//                    System.out.println(">>>>>>>>>>>>>>>>>  setPageTransformer "+page.getTag()+": "+position);
//
//                }
            }
        });
    }
}
