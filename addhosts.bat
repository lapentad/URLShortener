@echo off 

set "hostspath=%SystemRoot%\System32\drivers\etc\hosts"

echo 127.0.0.1 www.short.com >> "%hostspath%" 

exit 