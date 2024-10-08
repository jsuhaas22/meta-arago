DESCRIPTION = "Configuration files for SoftAP with udhcpd"
SECTION = "console/network"
HOMEPAGE = "http://udhcp.busybox.net/"
LICENSE = "GPL-2.0-only & BSD-4-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=de10de48642ab74318e893a61105afbb"

PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

# Default configuration file is from the base udhcp package
SRC_URI = "file://udhcpd.conf \
           file://LICENSE"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0755 ${S}/udhcpd.conf ${D}${sysconfdir}
}

FILES:${PN} += "${sysconfdir}/udhcpd.conf"
CONFFILES:${PN} += "${sysconfdir}/udhcpd.conf"
