package com.example.archive_manager.event;
import java.util.ArrayList;

public final class PlusEvent<TEventArgs extends EventArgs>{
    private ArrayList<IEventHandler<TEventArgs>> observerList
            =new ArrayList<IEventHandler<TEventArgs>>();

    public void raiseEvent(Object sender,TEventArgs e)
    {
        for(IEventHandler<TEventArgs> handler : this.observerList)
        {
            handler.eventReceived(sender,e);
        }
    }

    public void addPlusEventHandler(IEventHandler<TEventArgs> handler)
    {
        this.observerList.add(handler);
    }

    public void removePlusEventHandler(IEventHandler<TEventArgs> handler)
    {
        this.observerList.remove(handler);
    }
}
