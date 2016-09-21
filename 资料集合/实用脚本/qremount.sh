#!/bin/bash
echo "umounting..."
sudo umount ~/WindowsShare
echo "mounting..."
sudo mount -t cifs -o username=public //192.168.0.$1/资源共享 ~/WindowsShare
echo "done."
