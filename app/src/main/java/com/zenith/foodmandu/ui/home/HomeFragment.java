package com.zenith.foodmandu.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.zenith.foodmandu.R;
import com.zenith.foodmandu.ui.home.Adapter.CategoryAdapter;
import com.zenith.foodmandu.ui.home.Adapter.SuperAdapter;
import com.zenith.foodmandu.ui.home.Adapter.ViewPagerAdapter;
import com.zenith.foodmandu.ui.home.Model.OfferViewModel;
import com.zenith.foodmandu.ui.home.Model.SuperViewModel;
import com.zenith.foodmandu.ui.slideshow.SlideshowViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RecyclerView SuperRecyclerView;
    private RecyclerView OfferRecyclerView;
    ViewPager viewPager;
    public static List<HomeViewModel> categoryList=new ArrayList<>();
    public static List<SuperViewModel> superList=new ArrayList<>();
    //public static List<OfferViewModel> offerList=new ArrayList<>();
    private int position;
    private static final int PAGE_NUM=4;

    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(position,true);
            if(position>=PAGE_NUM) position=0;
            else position++;
            handler.postDelayed(runnable,3000);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        runnable.run();


        recyclerView = view.findViewById(R.id.recycler);
        HomeViewModel homeViewModel = new HomeViewModel("Foodmandu", R.drawable.logo);
        categoryList.add(new HomeViewModel("Restaurant", R.drawable.restaurant));

        categoryList.add(new HomeViewModel("Liquors", R.drawable.liquor));

        categoryList.add(new HomeViewModel("Refreshment", R.drawable.refresh));

        categoryList.add(new HomeViewModel("Bakeries", R.drawable.bakery));

        categoryList.add(new HomeViewModel("Snacks", R.drawable.snacks));

        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        SuperRecyclerView = view.findViewById(R.id.recyclerSuper);
        SuperViewModel superViewModel1 = new SuperViewModel(R.drawable.food, "Mo Mo Pasal", "Dish", "Bhaktapur", R.drawable.dis);
        superList = superViewModel1.getSuperList();
        superList.add(new SuperViewModel(R.drawable.coke, "Nepal Coke", "Coke","Patan",R.drawable.coke));
        superList.add(new SuperViewModel(R.drawable.pizza, "Pizza Nepal", "Pizza","Bhaktapur",R.drawable.pizza));
        superList.add(new SuperViewModel(R.drawable.burger, "Burger Time", "Burger","Butwal",R.drawable.burger));
        superList.add(new SuperViewModel(R.drawable.kfc, "KFC Nepal", "KFC","Kathmandu",R.drawable.kfc));
        superList.add(new SuperViewModel(R.drawable.nin, "Khaja Ghar", "Khaja","Bhaktapur",R.drawable.nin));
        SuperAdapter superAdapter = new SuperAdapter(getActivity(),superList);
        SuperRecyclerView.setAdapter(superAdapter);
        SuperRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        return view;


    }


}