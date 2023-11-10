package com.example.archive_manager.event;

public class PlusEventArgs extends EventArgs
{
    private int visa_num;
    public PlusEventArgs(int visa_num)
    {
        this.visa_num=visa_num;
    }

    public int getVisaNum()
    {
        return this.visa_num;
    }
}
