# springboot-conf-gateway-api-oauth2
-----------------------------------------------------
IN "10.0.0.137" do:
-----------------------------------------------------
sudo firewall-cmd --add-port=8765/tcp --permanent
sudo firewall-cmd --reload

sudo firewall-cmd --list-ports
sudo firewall-cmd --list-all

---Fermer le port 8765 -------------------------------
sudo firewall-cmd --permanent --remove-port=8765/tcp
sudo firewall-cmd --reload

sudo firewall-cmd --list-ports
sudo firewall-cmd --list-all
