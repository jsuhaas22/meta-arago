SUMMARY = "Task to install graphics packages on sdk target"
LICENSE = "MIT"
PR = "r10"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
    libegl-dev \
    libgl-dev \
    libgles1-dev \
    libgles2-dev \
    libgles3-dev \
    libdrm-dev \
    wayland-dev \
    weston-dev \
    ${@bb.utils.contains('MACHINE_FEATURES', 'gc320', 'ti-gc320-libs-dev', '', d)} \
"
