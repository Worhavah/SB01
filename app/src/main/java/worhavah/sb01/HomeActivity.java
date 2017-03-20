package worhavah.sb01;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import worhavah.sb01.adapter.AdvertisePagerViewAdapter;
import worhavah.sb01.demos.myview.VIewGrouponeActivity;
import worhavah.sb01.demos.myview.VIewcombinedActivity;
import worhavah.sb01.demos.myview.ViewoneAtivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout headerLayout;//头布局

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    private TextView tv1,tv2,tv3;


    // 广告轮播
    private ViewPager mAdverViewpager;
    // 广告集合
    private List<ImageView> adver_images = new ArrayList<ImageView>();
    private AdvertisePagerViewAdapter mAdvertiseAdapter; // 广告适配器
    private LinearLayout mDotLayout;

    public HomeActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);

        initHeaderView();// 初始化头部
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
         setSupportActionBar(toolbar);


        //StatusBarCompat.compat(this, 0xFFFF0000);
        StatusBarCompat.compat(this);

        tv1=(TextView) findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
        tv3=(TextView) findViewById(R.id.tv3);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);

        //StatusBarCompat.compat(this, getResources().getColor(R.color.status_bar_color));
        //StatusBarCompat.compat(this);
//        setSupportActionBar(toolbar);




        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(HomeActivity.this,100));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

        //mRecyclerView.addHeaderView(headerLayout);

        setupDrawerContent(mNavigationView);


      /*  mRecyclerView.addOnItemTouchListener(new ItemClickListener(mRecyclerView,
                new ItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                       // Log.i("touch click name:" + position);
                        Log.w("addOnItemTouchListener","touch click name:" + position);
                        Toast.makeText(HomeActivity.this, "touch click:" + position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                      //  DevLog.i("touch long click:" + position);
                        Log.w("click","ouch long click:" + position);
                        Toast.makeText(HomeActivity.this, "touch long click:" + position, Toast.LENGTH_SHORT).show();
                    }
                }));*/


        mRecyclerView.addOnItemTouchListener(new SingleItemClickListener(mRecyclerView,
                new SingleItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                       // DevLog.i("touch click name:" + position);
                        Toast.makeText(HomeActivity.this, "touch click:" + position, Toast.LENGTH_SHORT).show();
                        if(position==0){
                            startActivity(new Intent(HomeActivity.this, ViewoneAtivity.class));
                        }
                        if(position==1){
                            startActivity(new Intent(HomeActivity.this, VIewGrouponeActivity.class));
                        }
                        if(position==2){
                            startActivity(new Intent(HomeActivity.this, VIewcombinedActivity.class));
                        }


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        //DevLog.i("touch long click:" + position);
                        Toast.makeText(HomeActivity.this, "touch long click:" + position, Toast.LENGTH_SHORT).show();
                    }
                }));

    }


    /**
     * 初始化头布局
     */
    private void initHeaderView() {

        // 布局解析器，解析取得头部北荣
        headerLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.recyclerview_header, null);

        //头部控件初始化
        mAdverViewpager = (ViewPager) headerLayout.findViewById(R.id.ad_viewpager);//初始化广告轮播ViewPager
        mDotLayout = (LinearLayout) headerLayout.findViewById(R.id.dot_layout);//广告对应的dot
    }

    /**
     * 初始化控件
     */
    private void initializeViews() {


        /** 广告轮播 **/
        mAdvertiseAdapter = new AdvertisePagerViewAdapter(adver_images);
        mAdverViewpager.setAdapter(mAdvertiseAdapter);

       // mNewsRecyclerView = (RecyclerView) findViewById(R.id.news_rv);

       /* *//** 新闻 **//*
        // 设置布局显示方式，这里我使用都是垂直方式——LinearLayoutManager.VERTICAL
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        // 设置添加删除item的时候的动画效果
        mNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 新闻适配器
        mNewsAdapter = new NewsAdapter(this, newsData);
        mWrapAdapter = new WrapAdapter<>(mNewsAdapter);
        // 设置头部占据一行
        mWrapAdapter.adjustSpanSize(mNewsRecyclerView);
        // 设置RecyclerView的数据适配器(适配器包装)
        mNewsRecyclerView.setAdapter(mWrapAdapter);
        // 添加头布局
        mWrapAdapter.addHeaderView(headerLayout);

        //默认在1亿多
        mAdverViewpager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % adver_images.size()));
        //3秒定时
        mAdvertiseHandler.sendEmptyMessageDelayed(0, 2000);
        updateDot();*/

    }


    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener()
                {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        menuItem.setChecked(true);
                        //mDrawerLayout.closeDrawers();
                        if(menuItem.getTitle().equals("Friends")){
                            Toast.makeText(HomeActivity.this,"usoso",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(HomeActivity.this,"a"+menuItem.getTitle(),Toast.LENGTH_SHORT).show();

                        }
                        return true;
                    }
                });
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        mDatas.add("自定义viewDemo");
        mDatas.add("自定义viewGroup");
        mDatas.add("combinedview");
        mDatas.add("3");
        mDatas.add("4");
        mDatas.add("5");
        mDatas.add("6");
        mDatas.add("7");
        mDatas.add("1");
        mDatas.add("2");
        mDatas.add("3");
        mDatas.add("4");
        mDatas.add("5");
        mDatas.add("6");
        mDatas.add("7");
        mDatas.add("1");
        mDatas.add("2");
        mDatas.add("3");
        mDatas.add("4");
        mDatas.add("5");
        mDatas.add("6");
        mDatas.add("7");mDatas.add("1");
        mDatas.add("2");
        mDatas.add("3");
        mDatas.add("4");
        mDatas.add("5");
        mDatas.add("6");
        mDatas.add("7");


    }




    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {



        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    HomeActivity.this).inflate(R.layout.item_home, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position)
        {
            holder.tv.setText(mDatas.get(position));



             /*   holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.w("onBindViewHolder",mDatas.get(position)+"");
                    }
                });*/



        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }












}
