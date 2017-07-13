package harry.shopping;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText search_goods;
    ListView goods_list;
    List<Map<String,Object>> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_goods = (EditText) findViewById(R.id.search_goods);
        goods_list = (ListView) findViewById(R.id.goods_list);

        getData();
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.goods_item,new String[]{"image"
                ,"name","view","goods_name","goods_view","goods_price"},new int[]{R.id.goods_image
                ,R.id.name_text,R.id.view_text,R.id.goods_name_text,R.id.goods_view_text});
        goods_list.setAdapter(adapter);
        setListViewHeightBasedOnChildren(goods_list);

    }
    private  void getData(){
        list= new ArrayList<Map<String,Object>>();
        for(int i = 0;i<10;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",R.mipmap.ic_launcher);
            map.put("name","商品名称");
            map.put("view","商品详情");
            map.put("goods_name","good"+i);
            map.put("goods_view","goods"+i);
            map.put("goods_price",23+i);
            list.add(map);

        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
