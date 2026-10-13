arp -d*
ipconfig /flushdns
arp -d
ipconfig /displaydns
ping eagle-server.example.com
arp -a
ipconfig /displaydns
pause