package com.vinh.se161455_tranquangvinh;

import android.app.Dialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    ListView lvProducts;
    ArrayAdapter<Book> adapterProduct;
    List<Book> listProduct = new ArrayList<>();
    List<Author> listCategories = new ArrayList<>();
    Button buttonAdd, btnGoToAuthorAc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoToAuthorAc = findViewById(R.id.buttonViewAuthor);
        lvProducts = findViewById(R.id.lvProducts);
        buttonAdd = findViewById(R.id.btnAdd);
        listProduct = BookDAO.getAll(MainActivity.this);
        if (listProduct.size() == 0) {
            Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
        }
        listCategories = AuthorDAO.getAllCategories(MainActivity.this);
        adapterProduct = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listProduct);
        lvProducts.setAdapter(adapterProduct);

        buttonAdd.setOnClickListener(v -> {
            showAddProductDialog();
        });

        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            showUpdateProductDialog(position);
        });

        btnGoToAuthorAc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AuthorActivity.class);
            startActivity(intent);
        });
    }

    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_product, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        List<Integer> cateIDList = new ArrayList<>(); //Lấy list cateId từ list category trong DB
        for (Author c : listCategories) {
            cateIDList.add(c.getAuthorId());
        }

        EditText edtName = view.findViewById(R.id.edtAddBName);
        EditText edtDate  = view.findViewById(R.id.edtAddPBDate);
        EditText edtType  = view.findViewById(R.id.edtAddBType);
        EditText edtAuthorID  = view.findViewById(R.id.edtAddBAuthorID);
        Button buttonSaveProduct = view.findViewById(R.id.btnAddAuthor);

        buttonSaveProduct.setOnClickListener(v -> {
            if (!cateIDList.contains(Integer.parseInt(edtAuthorID.getText().toString()))) { // nếu nhập cate id ko có sẵn
                Toast.makeText(MainActivity.this, "ID tác giả không tồn tại", Toast.LENGTH_SHORT).show();
            } else {
                String productName = edtName.getText().toString();
                String date = edtDate.getText().toString();
                String type = edtType.getText().toString();
                int auID = Integer.parseInt(edtAuthorID.getText().toString());
                if (BookDAO.insert(MainActivity.this, productName, date, type, auID)) {
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    listProduct.clear(); // thêm thành công thì reload lại
                    listProduct.addAll(BookDAO.getAll(MainActivity.this));
                    adapterProduct.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showUpdateProductDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_product, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        List<Integer> cateIDList = new ArrayList<>(); //Lấy list cateId từ list category trong DB
        for (Author c : listCategories) {
            cateIDList.add(c.getAuthorId());
        }

        EditText edtName = view.findViewById(R.id.edtUpdateName);
        EditText date  = view.findViewById(R.id.edtUpdateDate);
        EditText type  = view.findViewById(R.id.edtUpdateType);
        EditText authorId  = view.findViewById(R.id.edtUpdateAuthorID);

        Button buttonUpdateProduct = view.findViewById(R.id.btnUpdatePro);
        Button buttonDeleteProduct = view.findViewById(R.id.buttonDeleteInUpdate);

        Book product = listProduct.get(position);
        edtName.setText(product.getBookName());
        date.setText(product.getPublicDate());
        type.setText(product.getType());
        authorId.setText(String.valueOf(product.getAuthorID()));

        buttonUpdateProduct.setOnClickListener(v -> {
            if (!cateIDList.contains(Integer.parseInt(authorId.getText().toString()))) { // nếu nhập cate id ko có sẵn
                Toast.makeText(MainActivity.this, "ID tác giả không tồn tại", Toast.LENGTH_SHORT).show();
            } else {
                product.setBookName(edtName.getText().toString());
                product.setPublicDate(date.getText().toString());
                product.setType(type.getText().toString());
                product.setAuthorID(Integer.parseInt(authorId.getText().toString()));
                if (BookDAO.update(MainActivity.this, product)) {
                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    listProduct.clear(); // thêm thành công thì reload lại
                    listProduct.addAll(BookDAO.getAll(MainActivity.this));
                    adapterProduct.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDeleteProduct.setOnClickListener(v -> {
            if (BookDAO.delete(MainActivity.this, product.getBookID())) {
                Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                listProduct.clear(); // thêm thành công thì reload lại
                listProduct.addAll(BookDAO.getAll(MainActivity.this));
                adapterProduct.notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

}