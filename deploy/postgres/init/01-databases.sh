#!/bin/bash
set -eou pipefail


create_service_db() {
  local name=$1 pass=$2

#   create a service role and database for the given service name and password
# also revoke public access to the database
  psql -v ON_ERROR_STOP=1 -U "$POSTGRES_USER" -d"$POSTGRES_DB" <<-EOSQL
    CREATE ROLE ${name}_svc LOGIN PASSWORD '${pass}';
    CREATE DATABASE ${name} OWNER ${name}_svc;

    REVOKE CONNECT ON DATABASE ${name} FROM PUBLIC;
EOSQL
}

create_service_db koboflow_core ${KOBOFLOW_CORE_POSTGRES_PASSWORD}
create_service_db koboflow_transfer ${KOBOFLOW_TRANSFER_POSTGRES_PASSWORD}
create_service_db koboflow_nip_sim ${KOBOFLOW_NIP_SIM_POSTGRES_PASSWORD}
create_service_db koboflow_statement ${KOBOFLOW_STATEMENT_POSTGRES_PASSWORD}
create_service_db koboflow_recon ${KOBOFLOW_RECON_POSTGRES_PASSWORD}
create_service_db koboflow_notification ${KOBOFLOW_NOTIFICATION_POSTGRES_PASSWORD}

create_service_db lab_koboflow ${LAB_KOBOFLOW_POSTGRES_PASSWORD}
create_service_db lab_partner ${LAB_PARTNER_POSTGRES_PASSWORD}
create_service_db lab_coordinator ${LAB_COORDINATOR_POSTGRES_PASSWORD}