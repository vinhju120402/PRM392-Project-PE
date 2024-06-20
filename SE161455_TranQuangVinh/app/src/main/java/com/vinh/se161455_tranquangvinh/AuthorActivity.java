package com.vinh.se161455_tranquangvinh;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AuthorActivity extends AppCompatActivity {
    ListView lvAuthor;
    ArrayAdapter<Author> adapterAuthor;
    List<Author> listAuthor = new ArrayList<>();
    List<Book> listBooks = new ArrayList<>();
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        lvAuthor = findViewById(R.id.lvAuthor);
        buttonAdd = findViewById(R.id.buttonAddAuthor);
        listAuthor = AuthorDAO.getAllCategories(AuthorActivity.this);
        if (listAuthor.size() == 0) {
            Toast.makeText(AuthorActivity.this, "Empty", Toast.LENGTH_SHORT).show();
        }
        listBooks = BookDAO.getAll(AuthorActivity.this);
        adapterAuthor = new ArrayAdapter<>(AuthorActivity.this, android.R.layout.simple_list_item_1, listAuthor);
        lvAuthor.setAdapter(adapterAuthor);

        buttonAdd.setOnClickListener(v -> {
            showAddProductDialog();
        });

//        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
//            showUpdateProductDialog(position);
//        });
    }

    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AuthorActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_author, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
//
//        List<Integer> cateIDList = new ArrayList<>(); //Lấy list cateId từ list category trong DB
//        for (Author c : listCategories) {
//            cateIDList.add(c.getAuthorId());
//        }

        EditText edtname  = view.findViewById(R.id.edtAddAName);
        EditText edtAdd  = view.findViewById(R.id.edtAddAAdress);
        EditText edtPhone  = view.findViewById(R.id.edtAddAPhone);
        Button buttonSaveProduct = view.findViewById(R.id.btnAddAuthor);

        buttonSaveProduct.setOnClickListener(v -> {
//            if (!cateIDList.contains(Integer.parseInt(edtAuthorID.getText().toString()))) { // nếu nhập cate id ko có sẵn
//                Toast.makeText(MainActivity.this, "ID tác giả không tồn tại", Toast.LENGTH_SHORT).show();
//            } else {
            String productName = edtname.getText().toString();
            String date = edtAdd.getText().toString();
            String type = edtPhone.getText().toString();
            if (AuthorDAO.insert(AuthorActivity.this, productName, date, type)) {
                Toast.makeText(AuthorActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                listAuthor.clear(); // thêm thành công thì reload lại
                listAuthor.addAll(AuthorDAO.getAllCategories(AuthorActivity.this));
                adapterAuthor.notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(AuthorActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
//            }
        });
    }
}
