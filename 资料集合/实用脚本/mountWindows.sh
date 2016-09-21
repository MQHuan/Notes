#!/bin/bash

ip=$(arp -a | grep d8:cb:8a:1a:f9:46 | awk {'print $2'} | sed 's/(//g' | sed 's/)//g')
if [ -z $ip ]; then
    echo "not found"
    timeout 10 ping -b 192.168.0.255
    sudo mount -t cifs -o username=public //$ip/资源共享 ~/WindowsShare
else
    echo $ip
    sudo mount -t cifs -o username=public //$ip/资源共享 ~/WindowsShare
fi

exit 0
    
