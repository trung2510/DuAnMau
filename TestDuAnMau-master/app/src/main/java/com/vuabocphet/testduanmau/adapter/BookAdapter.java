package com.vuabocphet.testduanmau.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vuabocphet.testduanmau.BookActivity;
import com.vuabocphet.testduanmau.R;
import com.vuabocphet.testduanmau.holder.HolderBook;
import com.vuabocphet.testduanmau.model.Book;


import java.util.ArrayList;

public class
BookAdapter extends RecyclerView.Adapter<HolderBook> {
        private final ArrayList<Book> bookArrayList;
        private final BookActivity context;

    public BookAdapter(ArrayList<Book> bookArrayList, BookActivity context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderBook onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_book,parent,false);
        return new HolderBook(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBook holder, final int position) {
            final Book book=bookArrayList.get(position);
            holder.txtNameBook.setText(book.getMaTheLoai());
            holder.txtPOS.setText(book.getSoLuong()+"");
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.deleteBook(book.getMaSach(),position);
                }
            });
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.updateBook(book.getMaSach(),book.getMaTheLoai(),book.getTacGia(),book.getNXB(),book.getGiaBia(),book.getSoLuong());
                }
            });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
}
